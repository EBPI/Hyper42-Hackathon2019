package nl.hyper42.kim.backend.dao;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.hyperledger.fabric.sdk.BlockEvent.TransactionEvent;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.ChaincodeResponse.Status;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.QueryByChaincodeRequest;
import org.hyperledger.fabric.sdk.SDKUtils;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The Class HLFInvoker is used to invoke methods on the hyperledger fabric
 * chaincode.
 *
 * @author Micha Wensveen
 */
@Component
@ParametersAreNonnullByDefault
public class HLFInvoker {

    private static final String CC_NAME = "kimblockchain";

    /**
     * Instanties logging for this class
     */
    private static final Logger LOG = LoggerFactory.getLogger(HLFInvoker.class);

    /**
     * location of the credential certificate
     */
    @Value("${credential_location_certificate}")
    private String credentialLocationCertificate;

    /**
     * location of the credential privatekey
     */
    @Value("${credential_location_privatekey}")
    private String credentialLocationPrivatekey;

    /**
     * the affiliation of the user used in the credential
     */
    @Value("${credential_affiliation}")
    private String credentialAffiliation;

    /**
     * the affiliation of the user used in the credential
     */
    @Value("${credential_msp_id}")
    private String credentialMspId;

    /**
     * field peersmap as a Map
     */
    @Value("#{${peers}}")
    private Map<String, String> peersMap;

    /**
     * field orderermap as Map
     */
    @Value("#{${orderers}}")
    private Map<String, String> ordererMap;

    /**
     * field channelname
     */
    @Value("${channel}")
    private String channelName;

    /**
     * Invoke the chaincode.
     *
     * @param function - The name of the function to call.
     * @param args - String[] needed by the function.
     * @return the result of the call or {@code null}
     * @throws HLFRuntimeException when a call to Hyperledger fabric fails,
     *             InterruptedException
     */
    @Nullable
    public String invokeChaincode(String function, String... args) throws InterruptedException {
        // first get the credentials of the user.
        User user =
                new BusinesscardMapper("user1", credentialLocationCertificate, credentialLocationPrivatekey, credentialAffiliation, credentialMspId).getUser();
        // get the Hyperledger Fabric client and the channel
        HFClient client = getHfClient(user);
        Channel channel = getChannel(client);

        // build the transaction proposal to submit to the chaincode
        TransactionProposalRequest transactionProposalRequest = client.newTransactionProposalRequest();
        ChaincodeID ccId = ChaincodeID.newBuilder().setName(CC_NAME).build();
        transactionProposalRequest.setChaincodeID(ccId);
        transactionProposalRequest.setFcn(function);
        transactionProposalRequest.setArgs(args);
        transactionProposalRequest.setProposalWaitTime(20 * 1000);
        try {
            // submit the proposal to the channel
            Collection<ProposalResponse> transactionProposalResponses = channel.sendTransactionProposal(transactionProposalRequest, channel.getPeers());

            // check if all responses are succes.
            Collection<ProposalResponse> successful = new LinkedList<>();
            for (ProposalResponse response : transactionProposalResponses) {
                if (response.getStatus() == Status.SUCCESS) {
                    LOG.debug("Successful transaction proposal response Txid: {} from peer {}", response.getTransactionID(), response.getPeer().getName());
                    successful.add(response);
                } else {
                    LOG.warn("An endorsers gave an error for invoke({}). {} endorser error:{}. Was verified:{}", function, response.getStatus().getStatus(),
                            response.getMessage(), response.isVerified());
                    channel.removePeer(response.getPeer());
                }
            }

            // Check that all the proposals are consistent with each other. We should have
            // only one set
            // Shown here as an example that applications can invoke and select.
            // where all the proposals above are consistent. Note the when sending to
            // Orderer this is done automatically.
            // See org.hyperledger.fabric.sdk.proposal.consistency_validation config
            // property.
            Collection<Set<ProposalResponse>> proposalConsistencySets = SDKUtils.getProposalConsistencySets(successful);
            if (proposalConsistencySets.size() != 1) {
                LOG.error("Problem with transaction: Expected only one set of consistent proposal responses but got {}", proposalConsistencySets.size());
                throw new HLFRuntimeException(
                        String.format("Expected only one set of consistent proposal responses but got %d", proposalConsistencySets.size()));
            }

            // get the actual payload that is returned by the chaincode.
            ProposalResponse resp = successful.iterator().next();

            // This is the data returned by the chaincode.
            byte[] payload = resp.getChaincodeActionResponsePayload();
            String resultAsString = null;
            if (payload != null) {
                resultAsString = new String(payload, UTF_8);
                LOG.trace("response for {}:\n{}", function, resultAsString);
            }

            // submit the endorsed transaction.
            TransactionEvent transactionEvent = channel.sendTransaction(successful).get(20, TimeUnit.SECONDS);
            LOG.debug("is transaction valid? " + transactionEvent.isValid());
            return resultAsString;

        } catch (ProposalException | InvalidArgumentException | ExecutionException | TimeoutException e) {
            LOG.error("Problem with transaction", e);
            throw new HLFRuntimeException("Problem with transaction", e);
        }
    }

    /**
     * Info from blockchain using query
     *
     * @param queryName - The name of the query to call
     * @param args - String[] needed by the function.
     * @return the result of the call or {@code null}
     */
    public String queryBlockChain(String queryName, String... args) {
        // first get the credentials of the user.
        User user =
                new BusinesscardMapper("user1", credentialLocationCertificate, credentialLocationPrivatekey, credentialAffiliation, credentialMspId).getUser();
        // get the Hyperledger Fabric client and the channel
        HFClient client = getHfClient(user);
        Channel channel = getChannel(client);

        // build the query proposal to submit to the blockchain
        QueryByChaincodeRequest queryRequest = client.newQueryProposalRequest();
        ChaincodeID ccId = ChaincodeID.newBuilder().setName(CC_NAME).build();
        queryRequest.setChaincodeID(ccId);

        // CC function to be called
        queryRequest.setFcn(queryName);
        queryRequest.setArgs(args);

        String resultAsString = null;
        try {
            // execute the query
            Collection<ProposalResponse> queryResponses = channel.queryByChaincode(queryRequest);
            // get response
            for (ProposalResponse response : queryResponses) {
                if (response.isInvalid()) {
                    LOG.error("response from query is invalid {}", response.getMessage());
                } else {
                    resultAsString = new String(response.getChaincodeActionResponsePayload(), UTF_8);
                }
                LOG.trace("result: {}", resultAsString);
            }
        } catch (InvalidArgumentException | ProposalException e) {
            LOG.error("Problem querying blockchain", e);
            throw new HLFRuntimeException("Problem querying blockchain", e);
        }
        return resultAsString;
    }

    @Nonnull
    private HFClient getHfClient(User user) {
        try {
            // initialize default cryptosuite
            CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
            // setup the client
            HFClient client = HFClient.createNewInstance();
            client.setCryptoSuite(cryptoSuite);
            client.setUserContext(user);
            return client;
        } catch (InvalidArgumentException | CryptoException | IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchMethodException
                | InvocationTargetException e) {
            LOG.error("Problem with creating new HfClient", e);
            throw new HLFRuntimeException("Problem with creating new HfClient", e);
        }
    }

    @Nonnull
    private Channel getChannel(HFClient client) {
        try {
            // channel name in network
            Channel channel = client.newChannel(channelName);

            peersMap.entrySet().stream().forEach(p -> addPeer(p, channel, client));
            ordererMap.entrySet().stream().forEach(o -> addOrderer(o, channel, client));

            channel.initialize();

            return channel;
        } catch (InvalidArgumentException | TransactionException e) {
            LOG.error("Error creating channel", e);
            throw new HLFRuntimeException("Error creating channel", e);
        }
    }

    private void addPeer(Entry<String, String> peerEntry, Channel channel, HFClient client) {
        // peers name and endpoint in network
        LOG.info("Adding peer {} to channel", peerEntry.getValue());
        try {
            Peer peer = client.newPeer(peerEntry.getKey(), peerEntry.getValue());
            channel.addPeer(peer);
        } catch (InvalidArgumentException e) {
            LOG.error("Error adding peer to channel", e);
            throw new HLFRuntimeException("Error adding peer to channel", e);
        }
    }

    private void addOrderer(Entry<String, String> ordererEntry, Channel channel, HFClient client) {
        // orderer name and endpoint in network
        LOG.info("Adding orderer {} to channel", ordererEntry.getValue());
        try {
            Orderer orderer = client.newOrderer(ordererEntry.getKey(), ordererEntry.getValue());
            channel.addOrderer(orderer);
        } catch (InvalidArgumentException e) {
            LOG.error("Error adding orderer to channel", e);
            throw new HLFRuntimeException("Error adding orderer to channel", e);
        }

    }
}
