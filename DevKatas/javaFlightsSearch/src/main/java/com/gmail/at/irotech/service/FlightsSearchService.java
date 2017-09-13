package com.gmail.at.irotech.service;

import com.gmail.at.irotech.comparator.FlightsSearchAvailableFlightFareComparator;
import com.gmail.at.irotech.exception.FlightsSearchException;
import com.gmail.at.irotech.web.client.CrazyAirClient;
import com.gmail.at.irotech.web.client.ToughJetClient;
import com.gmail.at.irotech.web.model.FlightsSearchAvailableFlight;
import com.gmail.at.irotech.web.model.FlightsSearchAvailableFlights;
import com.gmail.at.irotech.web.model.FlightsSearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class FlightsSearchService implements IFlightsSearchService {

    private static final Logger logger = LoggerFactory.getLogger(FlightsSearchService.class);

    private final RestTemplate restTemplate;

    @Autowired
    private CrazyAirClient crazyAirClient;

    @Autowired
    private ToughJetClient toughJetClient;

    public FlightsSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

//    http://localhost:8080/flightsSearch/search/PMO/STN/2017-01-25/2017-01-27/1
//    http://localhost:8080/crazyair/search/PMO/STN/07-20-2017%2008:30:00/07-22-2017%2018:30:00/1
//    http://localhost:8080/toughjet/search/PMO/STN/20/07/2017/22/07/2017/1

    @Override
    public List<FlightsSearchAvailableFlight> extractFlights(FlightsSearchRequest request) throws FlightsSearchException {
        logger.info("FlightsSearchService");

        List<FlightsSearchAvailableFlight> availableFlights;

        List<Future<FlightsSearchAvailableFlights>> futures = new ArrayList<>();
        futures.add(toughJetClient.asyncRequest(restTemplate, request));
        futures.add(crazyAirClient.asyncRequest(restTemplate, request));

        availableFlights = futures
                .stream()
                .map(t -> {
                    try {
                            return t.get();
                        } catch (InterruptedException ie) {
                            return Stream.of(ie);
                        } catch (ExecutionException ee) {
                            return Stream.of(ee);
                        }
                    })
                .flatMap(flights -> ((List<FlightsSearchAvailableFlight>) flights)
                        .stream()
                        .peek(System.out::println)
                )
                .collect(toList());

        logger.info("FlightsSearchService - stop. Found " + availableFlights.size() + " flights");

        Collections.sort(availableFlights, new FlightsSearchAvailableFlightFareComparator());
        return availableFlights;
    }

}
