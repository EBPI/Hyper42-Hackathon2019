#!/bin/bash
rm ../../../../target/credentials/user1 -rf
mkdir -p ../../../../target/credentials/user1
if [ -d "../../../../../blockchain-platform/bc-network/crypto-config" ];then
	# copy the crypto-config from the bc-network
  cp ../../../../../blockchain-platform/bc-network/crypto-config/peerOrganizations/org1.hyper42.nl/users/User1@org1.hyper42.nl/msp/signcerts/User1@org1.hyper42.nl-cert.pem ../../../../target/credentials/user1
  cp ../../../../../blockchain-platform/bc-network/crypto-config/peerOrganizations/org1.hyper42.nl/users/User1@org1.hyper42.nl/msp/signcerts/User1@org1.hyper42.nl-cert.pem ../../../../target/credentials/user1/certificate
  cp ../../../../../blockchain-platform/bc-network/crypto-config/peerOrganizations/org1.hyper42.nl/users/User1@org1.hyper42.nl/msp/keystore/* ../../../../target/credentials/user1/privateKey
else
	# use the dummy test data
  cp ../../../../src/test/resources/credentials/user1/User1@org1.hyper42.nl-cert.pem ../../../../target/credentials/user1
  cp ../../../../src/test/resources/credentials/user1/User1@org1.hyper42.nl-cert.pem ../../../../target/credentials/user1/certificate
  cp ../../../../src/test/resources/credentials/user1/privateKey ../../../../target/credentials/user1/privateKey
fi
