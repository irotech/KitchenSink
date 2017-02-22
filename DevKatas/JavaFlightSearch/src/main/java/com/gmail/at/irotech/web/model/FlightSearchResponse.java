package com.gmail.at.irotech.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FlightSearchResponse {

    private List<FlightSearchAvailableFlight> availableFlights;
    private String result;
    private String error;

    @JsonCreator
    public FlightSearchResponse(
            @JsonProperty("availableFlights") List<FlightSearchAvailableFlight> availableFlights,
            @JsonProperty("result") String result,
            @JsonProperty("error") String error) {
        this.availableFlights = availableFlights;
        this.result = result;
        this.error = error;
    }

    public List<FlightSearchAvailableFlight> getAvailableFlights() {
        return availableFlights;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

}
