package com.gmail.at.irotech.web.controller;

import com.gmail.at.irotech.FlightSearchApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlightSearchController.class)
@ContextConfiguration(classes = FlightSearchApplication.class)
public class FlightSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void healthCheck() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string("OKK"));
    }

    @Test
    public void busyFlightsSearchServiceWrongOrMissingOrigin() throws Exception {

    }

    @Test
    public void busyFlightsSearchServiceWrongOrMissingDestination() throws Exception {

    }

    @Test
    public void busyFlightsSearchServiceWrongOrMissingDepartureDate() throws Exception {

    }

    @Test
    public void busyFlightsSearchServiceWrongOrMissingReturnDate() throws Exception {

    }

    @Test
    public void busyFlightsSearchServiceWrongOrMissingNumberOfPassengers() throws Exception {

    }

    @Test
    public void busyFlightsSearchService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/busyflights/search/PMO/STN/2017-01-25/2017-01-27/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result").value("success"))
                .andExpect(jsonPath("$.error").value(""));
    }

}
