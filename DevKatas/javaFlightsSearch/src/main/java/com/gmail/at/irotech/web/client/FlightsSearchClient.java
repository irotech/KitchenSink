package com.gmail.at.irotech.web.client;

import com.gmail.at.irotech.web.model.FlightsSearchAvailableFlights;
import com.gmail.at.irotech.web.model.FlightsSearchRequest;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

public abstract class FlightsSearchClient {

    public abstract Future<FlightsSearchAvailableFlights> asyncRequest(RestTemplate restTemplate, FlightsSearchRequest request);

}
