package com.gmail.at.irotech;

import com.gmail.at.irotech.converter.BerlinClockConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by irotech on 14/12/2016.
 */
@SpringBootApplication
public class BerlinClockApplication {

    @Autowired
    private BerlinClockConverter berlinClockConverter;

    public static void main(String[] args) {
        SpringApplication.run(BerlinClockApplication.class, args);
    }

}
