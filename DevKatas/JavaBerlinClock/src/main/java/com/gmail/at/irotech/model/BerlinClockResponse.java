package com.gmail.at.irotech.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

/**
 * Created by irotech on 14/12/2016.
 */
@Value
@Builder
@RequiredArgsConstructor
@JsonDeserialize(builder = BerlinClockResponse.BerlinClockResponseBuilder.class)
public class BerlinClockResponse {

    private final String date = LocalDate.now().atStartOfDay().toString();
    private String inputClock;
    private String berlinClock;

    private String result;
    private String error;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class BerlinClockResponseBuilder {
    }

}
