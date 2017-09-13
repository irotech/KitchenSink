package com.gmail.at.irotech.web.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightsSearchAvailableFlights {

    private List<FlightsSearchAvailableFlight> availableFlights;

    @JsonCreator
    public FlightsSearchAvailableFlights(
            @JsonProperty(value = "availableFlights") List<FlightsSearchAvailableFlight> availableFlights) {
        this.availableFlights = availableFlights;
    }

    public List<FlightsSearchAvailableFlight> getAvailableFlights() {
        return availableFlights;
    }

    public void setAvailableFlights(List<FlightsSearchAvailableFlight> availableFlights) {
        this.availableFlights = availableFlights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightsSearchAvailableFlights that = (FlightsSearchAvailableFlights) o;

        return availableFlights != null ? availableFlights.equals(that.availableFlights) : that.availableFlights == null;
    }

    @Override
    public int hashCode() {
        return availableFlights != null ? availableFlights.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FlightsSearchAvailableFlights{" +
                "availableFlights=" + availableFlights +
                '}';
    }

}
