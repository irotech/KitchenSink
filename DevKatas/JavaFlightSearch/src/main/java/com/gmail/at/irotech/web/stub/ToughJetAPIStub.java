package com.gmail.at.irotech.web.stub;

import com.gmail.at.irotech.web.stub.vo.ToughJetResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * ToughJet API
 *
 * Request
 *
 * from 3 letter IATA code(eg. LHR, AMS)
 * to 3 letter IATA code(eg. LHR, AMS)
 * departureDay Day of the Month
 * departureMonth Month as an Integer(1-12)
 * departureYear 4 digit Year
 * returnDay Day of the Month
 * returnMonth Month as an Integer(1-12)
 * returnYear 4 digit Year
 * numberOfAdults Number of passengers
 *
 * Response
 *
 * carrier Name of the Airline
 * basePrice Price without tax(doesn’t include discount)
 * tax Tax which needs to be charged along with
 * the price
 * discount Discount which needs to be applied on the
 * price(in percentage)
 * departureAirportName 3 letter IATA code(eg. LHR, AMS)
 * arrivalAirportName 3 letter IATA code(eg. LHR, AMS)
 * departureDay Day of the Month
 * departureMonth Month as an Integer(1-12)
 * departureYear 4 digit Year
 * returnDay Day of the Month
 * returnMonth Month as an Integer(1-12)
 * returnYear 4 digit Year
 */
@RestController
@RequestMapping("/toughjet/search")
public class ToughJetAPIStub {

    private final String AIRLINE = "ToughJet";

    @RequestMapping(value = "/{from}/{to}/{departureDay}/{departureMonth}/{departureYear}/{returnDay}/{returnMonth}/{returnYear}/{numberOfAdults}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ToughJetResponse getToughJet(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable Integer departureDay,
            @PathVariable Integer departureMonth,
            @PathVariable Integer departureYear,
            @PathVariable Integer returnDay,
            @PathVariable Integer returnMonth,
            @PathVariable Integer returnYear,
            @PathVariable Integer numberOfAdults) {

        return new ToughJetResponse(AIRLINE, new BigDecimal(102), new BigDecimal(15), new BigDecimal(10), from, to,
                departureDay, departureMonth, departureYear, returnDay, returnMonth, returnYear, "success", "");
    }

}
