package com.gmail.at.irotech.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FlightSearchAvailableFlights {

    private List<FlightSearchAvailableFlight> availableFlights;

    @JsonCreator
    public FlightSearchAvailableFlights(
            @JsonProperty(value = "availableFlights") List<FlightSearchAvailableFlight> availableFlights) {
        this.availableFlights = availableFlights;
    }

    public List<FlightSearchAvailableFlight> getAvailableFlights() {
        return availableFlights;
    }

    public void setAvailableFlights(List<FlightSearchAvailableFlight> availableFlights) {
        this.availableFlights = availableFlights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightSearchAvailableFlights that = (FlightSearchAvailableFlights) o;

        return availableFlights != null ? availableFlights.equals(that.availableFlights) : that.availableFlights == null;
    }

    @Override
    public int hashCode() {
        return availableFlights != null ? availableFlights.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FlightSearchAvailableFlights{" +
                "availableFlights=" + availableFlights +
                '}';
    }

}
