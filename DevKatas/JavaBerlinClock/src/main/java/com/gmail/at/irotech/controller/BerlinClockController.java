package com.gmail.at.irotech.controller;

import com.gmail.at.irotech.converter.BerlinClockConverter;
import com.gmail.at.irotech.exception.BerlinClockInputException;
import com.gmail.at.irotech.model.BerlinClockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by irotech on 14/12/2016.
 */
@RestController //combines @Controller and @ResponseBody
public class BerlinClockController {

    @Autowired
    private BerlinClockConverter berlinClockConverter;

    private BerlinClockResponse response;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("redirect:/berlinClock");
    }

    @RequestMapping(value = "/berlinClock", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BerlinClockResponse berlinClockDefault() throws BerlinClockInputException {
        String clockConverter = berlinClockConverter.berlinClockConverter("00", "00", "00");
        return new BerlinClockResponse("00:00:00", clockConverter, "success", "");
    }

    @RequestMapping(value = "/berlinClock/{hours}/{minutes}/{seconds}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public BerlinClockResponse berlinClockConverter(@PathVariable String hours, @PathVariable String minutes, @PathVariable String seconds) {
        String clockConverter = null;
        String inputClock = hours + ":" + minutes + ":" + seconds;
        try {
            clockConverter = berlinClockConverter.berlinClockConverter(hours, minutes, seconds);
        } catch (BerlinClockInputException e) {
            return new BerlinClockResponse(inputClock, "broken-berlin-clock", "failure", e.getMessage());
        }
        return new BerlinClockResponse(inputClock, clockConverter, "success", "");
    }

}
