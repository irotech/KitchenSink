package com.gmail.at.irotech.controller;

import com.gmail.at.irotech.BerlinClockApplication;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by irotech on 14/12/2016.
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BerlinClockController.class)
@ContextConfiguration(classes = BerlinClockApplication.class)
public class BerlinClockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String inputClock;
    private String expectedBerlinClock;

    private String testDate;

    @Before
    public void setUp() throws Exception {
        testDate = LocalDate.now().atStartOfDay().toString();
    }

    @After
    public void tearDown() throws Exception {
        testDate = null;
        inputClock = null;
        expectedBerlinClock = null;
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(redirectedUrl("/berlinClock"));
    }

    @Test
    public void berlinClockDefault() throws Exception {
        inputClock = "00:00:00";
        expectedBerlinClock = "Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO";
        mockMvc.perform(MockMvcRequestBuilders.get("/berlinClock"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result").value("success"))
                .andExpect(jsonPath("$.inputClock").value(inputClock))
                .andExpect(jsonPath("$.berlinClock").value(expectedBerlinClock))
                .andExpect(jsonPath("$.date").value(testDate))
                .andExpect(jsonPath("$.error").value(""));
    }

    @Test
    public void berlinClockConverterKOForWrongHours() throws Exception {
        inputClock = "x:12:12";
        mockMvc.perform(MockMvcRequestBuilders.get("/berlinClock/x/12/12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result").value("failure"))
                .andExpect(jsonPath("$.inputClock").value(inputClock))
                .andExpect(jsonPath("$.berlinClock").value("broken-berlin-clock"))
                .andExpect(jsonPath("$.date").value(testDate))
                .andExpect(jsonPath("$.error").value("Please make sure the format for hours follows thi pattern: 0-23"));
    }

    @Test
    public void berlinClockConverterKOForWrongMinutes() throws Exception {
        inputClock = "12:x:12";
        mockMvc.perform(MockMvcRequestBuilders.get("/berlinClock/12/x/12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result").value("failure"))
                .andExpect(jsonPath("$.inputClock").value(inputClock))
                .andExpect(jsonPath("$.berlinClock").value("broken-berlin-clock"))
                .andExpect(jsonPath("$.date").value(testDate))
                .andExpect(jsonPath("$.error").value("Please make sure the format for minutes and seconds follows thi pattern: 0-59"));
    }

    @Test
    public void berlinClockConverterKOForWrongSeconds() throws Exception {
        inputClock = "12:12:x";
        mockMvc.perform(MockMvcRequestBuilders.get("/berlinClock/12/12/x"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result").value("failure"))
                .andExpect(jsonPath("$.inputClock").value(inputClock))
                .andExpect(jsonPath("$.berlinClock").value("broken-berlin-clock"))
                .andExpect(jsonPath("$.date").value(testDate))
                .andExpect(jsonPath("$.error").value("Please make sure the format for minutes and seconds follows thi pattern: 0-59"));
    }

    @Test
    public void berlinClockConverterOKForValues_12_30_30() throws Exception {
        inputClock = "12:30:30";
        expectedBerlinClock = "Y\nRROO\nRROO\nYYRYYROOOOO\nOOOO";
        mockMvc.perform(MockMvcRequestBuilders.get("/berlinClock/12/30/30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result").value("success"))
                .andExpect(jsonPath("$.inputClock").value(inputClock))
                .andExpect(jsonPath("$.berlinClock").value(expectedBerlinClock))
                .andExpect(jsonPath("$.date").value(testDate))
                .andExpect(jsonPath("$.error").value(""));
    }

    @Test
    public void berlinClockConverterOKForValues_14_49_45() throws Exception {
        inputClock = "14:49:45";
        expectedBerlinClock = "O\nRROO\nRRRR\nYYRYYRYYROO\nYYYY";
        mockMvc.perform(MockMvcRequestBuilders.get("/berlinClock/14/49/45"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result").value("success"))
                .andExpect(jsonPath("$.inputClock").value(inputClock))
                .andExpect(jsonPath("$.berlinClock").value(expectedBerlinClock))
                .andExpect(jsonPath("$.date").value(testDate))
                .andExpect(jsonPath("$.error").value(""));
    }

    @Test
    public void berlinClockConverterOKForValues_23_59_59() throws Exception {
        inputClock = "23:59:59";
        expectedBerlinClock = "O\nRRRR\nRRRO\nYYRYYRYYRYY\nYYYY";
        mockMvc.perform(MockMvcRequestBuilders.get("/berlinClock/23/59/59"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result").value("success"))
                .andExpect(jsonPath("$.inputClock").value(inputClock))
                .andExpect(jsonPath("$.berlinClock").value(expectedBerlinClock))
                .andExpect(jsonPath("$.date").value(testDate))
                .andExpect(jsonPath("$.error").value(""));
    }

}
