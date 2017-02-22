package com.gmail.at.irotech.web.client;

import com.gmail.at.irotech.utils.DateUtils;
import com.gmail.at.irotech.web.model.FlightSearchAvailableFlight;
import com.gmail.at.irotech.web.model.FlightSearchAvailableFlights;
import com.gmail.at.irotech.web.model.FlightSearchRequest;
import com.gmail.at.irotech.web.stub.vo.CrazyAirResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * docs
 *
 * http://localhost:8080/crazyair/search/PMO/STN/07-20-2017%2008:30:00/07-22-2017%2018:30:00/1
 */
@Component
public class CrazyAirClient extends FlightSearchClient {

    private static final Logger logger = LoggerFactory.getLogger(CrazyAirClient.class);

    @Value("${3rd.api.endpoint.crazyair:http://localhost:8080/crazyair/search/%s/%s/%s/%s/%s}")
    String URL;

    @Async
    @Override
    public Future<FlightSearchAvailableFlights> asyncRequest(RestTemplate restTemplate, FlightSearchRequest request) {
        logger.info("CrazyAirClient - start ");

        List<FlightSearchAvailableFlight> availableFlights = new ArrayList<>();

        String crazyAirURL = String.format(URL,
                request.getOrigin(),
                request.getDestination(),
                DateUtils.dateToString(request.getDepartureDate()),
                DateUtils.dateToString(request.getReturnDate()),
                request.getNumberOfPassengers()
        );

        final CrazyAirResponse crazyAirResponse = restTemplate.getForObject(crazyAirURL, CrazyAirResponse.class);

        if(null != crazyAirResponse && crazyAirResponse.getResult().equalsIgnoreCase("success")) {
            crazyAirResponse.getCrazyAirAvailableFlightList().stream().forEach(fight -> {
                availableFlights.add(new FlightSearchAvailableFlight(
                        fight.getAirline(),
                        fight.getPrice(),
                        fight.getCabinClass(),
                        fight.getDepartureAirportCode(),
                        fight.getDestinationAirportCode(),
                        fight.getDepartureDate(),
                        fight.getArrivalDate()));
            });
        }
        logger.info("CrazyAirClient - stop ");

        return new AsyncResult<>(new FlightSearchAvailableFlights(availableFlights));
    }

}
