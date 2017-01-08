package com.gmail.at.irotech.converter;

import com.gmail.at.irotech.exception.BerlinClockInputException;
import com.gmail.at.irotech.utils.BerlinClockStatus;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by irotech on 13/12/2016.
 */
@Component
public class BerlinClockConverter {

    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    private final String berlinClockHoursPattern = "([01]?[0-9]|2[0-4])";
    private final String berlinClockMinutesAndSecondsPattern = "([0-5]?[0-9])";

    /**
     * Main method
     * @param seconds
     * @return
     */
    public String berlinClockConverter(String hours, String minutes, String seconds) throws BerlinClockInputException {
        StringBuilder conversion = new StringBuilder();
        if(hours.matches(berlinClockHoursPattern)) {
            if(minutes.matches(berlinClockMinutesAndSecondsPattern) && seconds.matches(berlinClockMinutesAndSecondsPattern)) {
                conversion.append(getTopLampAsSeconds(Integer.parseInt(seconds))).append("\n");
                int hs = Integer.parseInt(hours);
                conversion.append(getFirstLineLampsAsHours(hs)).append("\n");
                conversion.append(getSecondLineLampsAsResidualHours(hs)).append("\n");
                int mins = Integer.parseInt(minutes);
                conversion.append(getThirdLineLampsAsMinutes(mins)).append("\n");
                conversion.append(getForthLineLampsAsResidualMinutes(mins));
            } else {
                final String msg = "Please make sure the format for minutes and seconds follows thi pattern: 0-59";
                LOGGER.log(Level.SEVERE, msg);
                throw new BerlinClockInputException(msg);
            }
        } else {
            final String msg = "Please make sure the format for hours follows thi pattern: 0-23";
            LOGGER.log(Level.SEVERE, msg);
            throw new BerlinClockInputException(msg);
        }
        LOGGER.info("The berlinClock representation for the input: " + hours + ":" + minutes + ":" + seconds + "\n" + conversion.toString());
        return conversion.toString();
    }

    protected String getTopLampAsSeconds(int seconds) {
        if(seconds % 2 == 0) {
            return BerlinClockStatus.YELLOW.getColor();
        }
        return BerlinClockStatus.OFF.getColor();
    }

    protected String getFirstLineLampsAsHours(int hours) {
        final int lampsNumber = 4;
        return lampsDecoder(lampsNumber, (hours - (hours % 5))/5, BerlinClockStatus.RED.getColor());
    }

    protected String getSecondLineLampsAsResidualHours(int hours) {
        final int lampsNumber = 4;
        return lampsDecoder(lampsNumber, hours % 5, BerlinClockStatus.RED.getColor());
    }

    protected String getThirdLineLampsAsMinutes(int minutes) {
        final int lampsNumber = 11;
        return lampsDecoder(lampsNumber, (minutes - (minutes % 5))/5, BerlinClockStatus.YELLOW.getColor(), true);
    }

    protected String getForthLineLampsAsResidualMinutes(int minutes) {
        final int lampsNumber = 4;
        return lampsDecoder(lampsNumber, minutes % 5, BerlinClockStatus.YELLOW.getColor());
    }

    private String lampsDecoder(int lampsInTheRow, int lampsTurnedOn, String lampsColor) {
        return lampsDecoder(lampsInTheRow, lampsTurnedOn, lampsColor, false);
    }

    private String lampsDecoder(int lampsInTheRow, int lampsTurnedOn, String lampsColor, boolean mixedLamps) {
        final StringBuilder retVal = new StringBuilder();
        int yellowLampSequence = 0;
        for(int i=0; i < lampsTurnedOn; i++) {
            if(mixedLamps) {
                if(yellowLampSequence == 2) {
                    retVal.append(BerlinClockStatus.RED.getColor());
                    yellowLampSequence = 0;
                    continue;
                } else {
                    yellowLampSequence++;
                }
            }
            retVal.append(lampsColor);
        }
        for(int j=0; j < (lampsInTheRow - lampsTurnedOn); j++) {
            retVal.append("O");
        }
        return retVal.toString();
    }

    public static void main(String[] args) throws BerlinClockInputException {
        BerlinClockConverter converter = new BerlinClockConverter();
        String resVale = converter.berlinClockConverter(args[0], args[1], args[2]);
        System.out.println(">> Output: BerlinClock representation for the input: " + args[0] + ":" + args[1] + ":" +args[2]);
        System.out.println(resVale);
    }

}
