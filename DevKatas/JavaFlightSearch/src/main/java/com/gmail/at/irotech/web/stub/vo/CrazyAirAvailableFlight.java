package com.gmail.at.irotech.web.stub.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class CrazyAirAvailableFlight {

    String airline; //Name of the airline
    BigDecimal price; //Total price
    String cabinClass; //E for Economy and B for Business
    String departureAirportCode;  //Eg: LHR
    String destinationAirportCode; //Eg: LHR
    Date departureDate; //mm-dd-yyyy HH:MM:ss
    Date arrivalDate; //mm-dd-yyyy HH:MM:ss

    @JsonCreator
    public CrazyAirAvailableFlight(
            @JsonProperty("airline") String airline,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("cabinClass") String cabinClass,
            @JsonProperty("departureAirportCode") String departureAirportCode,
            @JsonProperty("destinationAirportCode") String destinationAirportCode,
            @JsonProperty("departureDate") Date departureDate,
            @JsonProperty("arrivalDate") Date arrivalDate) {
        this.airline = airline;
        this.price = price;
        this.cabinClass = cabinClass;
        this.departureAirportCode = departureAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public String getAirline() {
        return airline;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

}
