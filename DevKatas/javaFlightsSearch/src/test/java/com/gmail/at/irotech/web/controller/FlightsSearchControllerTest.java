package com.gmail.at.irotech.web.controller;

import com.gmail.at.irotech.FlightsSearchApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlightsSearchController.class)
@ContextConfiguration(classes = FlightsSearchApplication.class)
public class FlightsSearchControllerTest {

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
    public void flightsSearchServiceWrongOrMissingOrigin() throws Exception {

    }

    @Test
    public void flightsSearchServiceWrongOrMissingDestination() throws Exception {

    }

    @Test
    public void flightsSearchServiceWrongOrMissingDepartureDate() throws Exception {

    }

    @Test
    public void flightsSearchServiceWrongOrMissingReturnDate() throws Exception {

    }

    @Test
    public void flightsSearchServiceWrongOrMissingNumberOfPassengers() throws Exception {

    }

    @Test
    public void flightsSearchService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/flightsSearch/search/PMO/STN/2017-01-25/2017-01-27/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result").value("success"))
                .andExpect(jsonPath("$.error").value(""));
    }

}
