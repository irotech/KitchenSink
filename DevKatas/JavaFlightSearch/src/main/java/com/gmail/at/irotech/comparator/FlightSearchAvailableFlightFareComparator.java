package com.gmail.at.irotech.comparator;

import com.gmail.at.irotech.web.model.FlightSearchAvailableFlight;

import java.util.Comparator;

public class FlightSearchAvailableFlightFareComparator implements Comparator<FlightSearchAvailableFlight> {

    //DESC ordering
    @Override
    public int compare(FlightSearchAvailableFlight o1, FlightSearchAvailableFlight o2) {
        if(o1 == o2) {
            return 0;
        }
        if(o1 == null || o1.getFare() == null) {
            return o2 == null || o2.getFare() == null ? 0 : -1;
        }
        if(o2 == null || o2.getFare() == null) {
            return 1;
        }
        return o2.getFare().compareTo(o1.getFare());
//        return o1.getFare().compareTo(o2.getFare());
    }

}
