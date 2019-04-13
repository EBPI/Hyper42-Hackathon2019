package nl.hyper42.kim.offchain.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Optional;
import nl.hyper42.kim.offchain.model.generated.api.ResultID;
import nl.hyper42.kim.offchain.model.generated.api.TravelData;
import nl.hyper42.kim.offchain.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Rest Controller to interact with the Offchain microservice.
 *
 * @author Micha Wensveen
 */
@RestController
@RequestMapping("/data")
public final class DataController {

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(DataController.class);

    /** The data service. */
    @Autowired
    private DataService dataService;

    /**
     * Get a TravelData document based on its ID.
     *
     * @param id ID (primary key) of TravelData document
     * @return Found TravelData as JsonString
     */
    @GetMapping(value = "/{id}", produces = { "application/json" })
    @ApiOperation(value = "Gather the (originally added) Data with the given ID", notes = "Gather a single TravelData document by its ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "TravelData document"),
            @ApiResponse(code = 204, message = "TravelData documents for given ID have not been found"),
            @ApiResponse(code = 500, message = "A technical error has occurred when attempting to query TravelData documents") })
    public @ResponseBody ResponseEntity<TravelData> searchTravelDataById(
            @ApiParam(value = "The ID of the Data document", required = true) @PathVariable String id) {
        LOG.debug("DataController -> searchDataById: Starting query with ID {}", id);
        Optional<TravelData> data = dataService.gatherDataById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    /**
     * Add a new TravelData document.
     *
     * @param dataRequest The TravelData
     * @return the id of the stored data.
     */
    @PostMapping(value = "/", produces = { "application/json" })
    @ApiOperation(value = "Add a new TravelData for the Traveler")
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "ID"), @ApiResponse(code = 400, message = "An error has occurred when attempting to parse request"),
                    @ApiResponse(code = 500, message = "A technical error has occurred when attempting to add data") })
    public @ResponseBody ResponseEntity<ResultID> submitData(
            @ApiParam(value = "The data request: TravelData", required = true) @RequestBody TravelData dataRequest) {

        LOG.trace("DataController -> submitData: Starting query with data {}", dataRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultID().withId(dataService.submitData(dataRequest)));
    }
}