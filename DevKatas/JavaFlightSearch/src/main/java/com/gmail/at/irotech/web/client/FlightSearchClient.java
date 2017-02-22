package com.gmail.at.irotech.web.client;

import com.gmail.at.irotech.web.model.FlightSearchAvailableFlights;
import com.gmail.at.irotech.web.model.FlightSearchRequest;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

public abstract class FlightSearchClient {

    public abstract Future<FlightSearchAvailableFlights> asyncRequest(RestTemplate restTemplate, FlightSearchRequest request);

}
