package com.gmail.at.irotech.web.controller;

import com.gmail.at.irotech.exception.FlightSearchException;
import com.gmail.at.irotech.service.IFlightSearchService;
import com.gmail.at.irotech.utils.DateUtils;
import com.gmail.at.irotech.web.model.FlightSearchAvailableFlight;
import com.gmail.at.irotech.web.model.FlightSearchRequest;
import com.gmail.at.irotech.web.model.FlightSearchResponse;
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
public class FlightSearchController {

    @Autowired
    private IFlightSearchService FlightSearchService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String healthCheck() {
        return "OKK";
    }

    @RequestMapping(value = "/FlightSearch/search/{origin}/{destination}/{departureDate}/{returnDate}/{numberOfPassengers}",
            method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public FlightSearchResponse FlightSearchSearchService(
            @PathVariable String origin,
            @PathVariable String destination,
            @PathVariable String departureDate,
            @PathVariable(required = false) String returnDate,
            @PathVariable int numberOfPassengers) throws ExecutionException, InterruptedException {

        //TODO input validation
        FlightSearchRequest request = new FlightSearchRequest(origin, destination, DateUtils.stringInputDateToDate(departureDate), DateUtils.stringInputDateToDate(returnDate), numberOfPassengers);
        List<FlightSearchAvailableFlight> flights = null;

        try {
            flights = FlightSearchService.extractFlights(request);
        } catch (FlightSearchException e) {
            return new FlightSearchResponse(flights, "failure", e.getMessage());
        }
        return new FlightSearchResponse(flights,"success", "");
    }

}
