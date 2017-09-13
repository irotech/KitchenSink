package com.gmail.at.irotech.web.stub.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * CrazyAir API
 *
 * Response
 *
 * The response provided by CrazyAir contains a list of flights with the following attributes:
 *
 * airline Name of the airline
 * price Total price
 * cabinclass E for Economy and B for Business
 * departureAirportCode Eg: LHR
 * destinationAirportCode Eg: LHR
 * departureDate mm-dd-yyyy HH:MM:ss
 * arrivalDate mm-dd-yyyy HH:MM:ss
 */
public class CrazyAirResponse {

    private List<CrazyAirAvailableFlight> crazyAirAvailableFlightList;
    private String result;
    private String error;

    @JsonCreator
    public CrazyAirResponse(
            @JsonProperty("crazyAirAvailableFlightList") List<CrazyAirAvailableFlight> crazyAirAvailableFlightList,
            @JsonProperty("result") String result,
            @JsonProperty("error") String error) {
        this.crazyAirAvailableFlightList = crazyAirAvailableFlightList;
        this.result = result;
        this.error = error;
    }

    public List<CrazyAirAvailableFlight> getCrazyAirAvailableFlightList() {
        return crazyAirAvailableFlightList;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

}

