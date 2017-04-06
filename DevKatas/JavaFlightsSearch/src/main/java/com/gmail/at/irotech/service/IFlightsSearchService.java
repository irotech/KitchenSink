package com.gmail.at.irotech.service;

import com.gmail.at.irotech.exception.FlightsSearchException;
import com.gmail.at.irotech.web.model.FlightsSearchAvailableFlight;
import com.gmail.at.irotech.web.model.FlightsSearchRequest;

import java.util.List;

public interface IFlightsSearchService {

    List<FlightsSearchAvailableFlight> extractFlights(FlightsSearchRequest request) throws FlightsSearchException;

}
