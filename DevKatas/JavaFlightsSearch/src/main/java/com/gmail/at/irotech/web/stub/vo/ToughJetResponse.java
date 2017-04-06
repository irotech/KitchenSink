package com.gmail.at.irotech.web.stub.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * ToughJet API
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
public class ToughJetResponse {

    private String carrier; //Name of the Airline
    private BigDecimal basePrice; //Price without tax(doesn’t include discount)
    private BigDecimal tax; //Tax which needs to be charged along with the price
    private BigDecimal discount; //Discount which needs to be applied on the price(in percentage)
    private String departureAirportName; //3 letter IATA code(eg. LHR, AMS)
    private String arrivalAirportName; //3 letter IATA code(eg. LHR, AMS)
    private Integer departureDay; //Day of the Month
    private Integer departureMonth; //Month as an Integer(1-12)
    private Integer departureYear; //4 digit Year
    private Integer returnDay; //Day of the Month
    private Integer returnMonth; //Month as an Integer(1-12)
    private Integer returnYear; //4 digit Year
    private String result;
    private String error;

    @JsonCreator
    public ToughJetResponse(
            @JsonProperty("carrier") String carrier,
            @JsonProperty("basePrice") BigDecimal basePrice,
            @JsonProperty("tax") BigDecimal tax,
            @JsonProperty("discount") BigDecimal discount,
            @JsonProperty("departureAirportName") String departureAirportName,
            @JsonProperty("arrivalAirportName") String arrivalAirportName,
            @JsonProperty("departureDay") Integer departureDay,
            @JsonProperty("departureMonth") Integer departureMonth,
            @JsonProperty("departureYear") Integer departureYear,
            @JsonProperty("returnDay") Integer returnDay,
            @JsonProperty("returnMonth") Integer returnMonth,
            @JsonProperty("returnYear") Integer returnYear,
            @JsonProperty("result") String result,
            @JsonProperty("error") String error) {
        this.carrier = carrier;
        this.basePrice = basePrice;
        this.tax = tax;
        this.discount = discount;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departureDay = departureDay;
        this.departureMonth = departureMonth;
        this.departureYear = departureYear;
        this.returnDay = returnDay;
        this.returnMonth = returnMonth;
        this.returnYear = returnYear;
        this.result = result;
        this.error = error;
    }

    public String getCarrier() {
        return carrier;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public Integer getDepartureDay() {
        return departureDay;
    }

    public Integer getDepartureMonth() {
        return departureMonth;
    }

    public Integer getDepartureYear() {
        return departureYear;
    }

    public Integer getReturnDay() {
        return returnDay;
    }

    public Integer getReturnMonth() {
        return returnMonth;
    }

    public Integer getReturnYear() {
        return returnYear;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

}
