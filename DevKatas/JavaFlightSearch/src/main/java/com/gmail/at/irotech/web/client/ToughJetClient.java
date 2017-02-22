package com.gmail.at.irotech.web.client;

import com.gmail.at.irotech.utils.DateUtils;
import com.gmail.at.irotech.web.model.FlightSearchAvailableFlight;
import com.gmail.at.irotech.web.model.FlightSearchAvailableFlights;
import com.gmail.at.irotech.web.model.FlightSearchRequest;
import com.gmail.at.irotech.web.stub.vo.ToughJetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * docs
 *
 * http://localhost:8080/toughjet/search/PMO/STN/20/07/2017/22/07/2017/1
 */
@Component
public class ToughJetClient extends FlightSearchClient {

    private static final Logger logger = LoggerFactory.getLogger(ToughJetClient.class);

    @Value("${3rd.api.endpoint.toughjet:http://localhost:8080/toughjet/search/%s/%s/%s/%s/%s/%s/%s/%s/%s}")
    String URL;

    @Async
    @Override
    public Future<FlightSearchAvailableFlights> asyncRequest(RestTemplate restTemplate, FlightSearchRequest request) {
        logger.info("ToughJetClient - start ");

        List<FlightSearchAvailableFlight> availableFlights = new ArrayList<>();

        LocalDate departureLocalDate = request.getDepartureDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate returnLocalDate = request.getDepartureDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String toughJetURL = String.format(URL,
                request.getOrigin(),
                request.getDestination(),
                departureLocalDate.getDayOfWeek().getValue(),
                departureLocalDate.getMonthValue(),
                departureLocalDate.getYear(),
                returnLocalDate.getDayOfWeek().getValue(),
                returnLocalDate.getMonthValue(),
                returnLocalDate.getYear(),
                request.getNumberOfPassengers()
        );

        ToughJetResponse toughJetResponse = restTemplate.getForObject(toughJetURL, ToughJetResponse.class);
        if(null != toughJetResponse) {
            BigDecimal price = toughJetResponse.getBasePrice().add(toughJetResponse.getTax());
            BigDecimal discount = price.multiply(toughJetResponse.getDiscount().divide(BigDecimal.valueOf(100)));
            availableFlights.add(new FlightSearchAvailableFlight(
                    toughJetResponse.getCarrier(),
                    price.subtract(discount),
                    "N/A",
                    toughJetResponse.getDepartureAirportName(),
                    toughJetResponse.getArrivalAirportName(),
                    DateUtils.stringDateToDate(toughJetResponse.getDepartureDay(), toughJetResponse.getDepartureMonth(), toughJetResponse.getDepartureYear()),
                    DateUtils.stringDateToDate(toughJetResponse.getReturnDay(), toughJetResponse.getReturnMonth(), toughJetResponse.getReturnYear())
            ));
        }
        logger.info("ToughJetClient - stop ");

        return new AsyncResult<>(new FlightSearchAvailableFlights(availableFlights));
    }

}
