package com.gmail.at.irotech.web.stub;

import com.gmail.at.irotech.utils.DateUtils;
import com.gmail.at.irotech.web.stub.vo.CrazyAirAvailableFlight;
import com.gmail.at.irotech.web.stub.vo.CrazyAirResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CrazyAir API@JsonCreator
 *
 * Request
 *
 * origin 3 letter IATA code(eg. LHR, AMS)
 * destination 3 letter IATA code(eg. LHR, AMS)
 * departureDate mm-dd-yyyy
 * returnDate mm-dd-yyyy
 * numberOfPassengers Number of passengers
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
@RestController
@RequestMapping("/crazyair/search")
public class CrazyAirAPIStub {

    private final String AIRLINE = "CrazyAir";

    @RequestMapping(value = "/{origin}/{destination}/{departureDate}/{returnDate}/{numberOfPassengers}",
            method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public CrazyAirResponse getCrazyAir(
            @PathVariable String origin,
            @PathVariable String destination,
            @PathVariable String departureDate,
            @PathVariable String returnDate,
            @PathVariable Integer numberOfPassengers) {

        LocalDateTime departureTime = LocalDateTime.parse(departureDate, DateUtils.atTimeFormatter);
        LocalDateTime returnTime = LocalDateTime.parse(returnDate, DateUtils.atTimeFormatter);

        LocalDateTime departureTimeStubFlight1 = departureTime.plusHours(1);
        LocalDateTime departureTimeStubFlight2 = departureTime.plusHours(3);
        LocalDateTime returnTimeStubFlight1 = returnTime.plusHours(4);
        LocalDateTime returnTimeStubFlight2 = returnTime.plusHours(5);

        List<CrazyAirAvailableFlight> stubList = new ArrayList<>();

        stubList.add(new CrazyAirAvailableFlight(AIRLINE, new BigDecimal(79), "E", origin, destination,
                Date.from(departureTimeStubFlight1.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(returnTimeStubFlight1.atZone(ZoneId.systemDefault()).toInstant())));

        stubList.add(new CrazyAirAvailableFlight(AIRLINE, new BigDecimal(135), "E", origin, destination,
                Date.from(departureTimeStubFlight2.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(returnTimeStubFlight2.atZone(ZoneId.systemDefault()).toInstant())));

        return new CrazyAirResponse(stubList, "success", "");
    }

}
