package com.gmail.at.irotech.web.controller;

import com.gmail.at.irotech.exception.FlightsSearchException;
import com.gmail.at.irotech.service.IFlightsSearchService;
import com.gmail.at.irotech.utils.DateUtils;
import com.gmail.at.irotech.web.model.FlightsSearchAvailableFlight;
import com.gmail.at.irotech.web.model.FlightsSearchRequest;
import com.gmail.at.irotech.web.model.FlightsSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * docs
 */
@RestController
@RequestMapping("/flightsSearch")
public class FlightsSearchController {

    @Autowired
    private IFlightsSearchService FlightsSearchService;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String healthCheck() {
//        return "OKK";
//    }

    @RequestMapping(value = "/search/{origin}/{destination}/{departureDate}/{returnDate}/{numberOfPassengers}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public FlightsSearchResponse FlightsSearchSearchService(
            @PathVariable String origin,
            @PathVariable String destination,
            @PathVariable String departureDate,
            @PathVariable(required = false) String returnDate,
            @PathVariable int numberOfPassengers) throws ExecutionException, InterruptedException {

        //TODO input validation
        FlightsSearchRequest request = new FlightsSearchRequest(origin, destination, DateUtils.stringInputDateToDate(departureDate), DateUtils.stringInputDateToDate(returnDate), numberOfPassengers);
        List<FlightsSearchAvailableFlight> flights = null;

        try {
            flights = FlightsSearchService.extractFlights(request);
        } catch (FlightsSearchException e) {
            return new FlightsSearchResponse(flights, "failure", e.getMessage());
        }
        return new FlightsSearchResponse(flights,"success", "");
    }

}
