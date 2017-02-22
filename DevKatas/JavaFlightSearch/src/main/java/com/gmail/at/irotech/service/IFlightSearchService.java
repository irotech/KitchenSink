package com.gmail.at.irotech.service;

import com.gmail.at.irotech.exception.FlightSearchException;
import com.gmail.at.irotech.web.model.FlightSearchAvailableFlight;
import com.gmail.at.irotech.web.model.FlightSearchRequest;

import java.util.List;

public interface IFlightSearchService {

    List<FlightSearchAvailableFlight> extractFlights(FlightSearchRequest request) throws FlightSearchException;

}
