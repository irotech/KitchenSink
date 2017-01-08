package com.gmail.at.irotech.converter;

import com.gmail.at.irotech.exception.BerlinClockInputException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.gmail.at.irotech.utils.BerlinClockStatus.OFF;
import static com.gmail.at.irotech.utils.BerlinClockStatus.YELLOW;
import static org.junit.Assert.assertEquals;

/**
 * Created by irotech on 13/12/2016.
 *
 * Requirements:
 * 1) Implement the Berlin Clock as a function of the three parameters : hours (24-based), minutes, seconds and return a multi line string.
 * 2) Find a reasonable representation for the colours and states.
 * 3) Provide instructions on how to run it.
 *
 * Berlin Clock description: The time is calculated by adding the lit rectangular lamps.
 *
 * The top lamp is a pump which is blinking on/off every two seconds.
 * In the topmost line of red lamps every lamp represents 5 hours.
 * In the second line of red lamps every lamp represents 1 hour.
 *
 * So if in the first line 2 lamps are lit and in the second line 3 lamps its 5+5+3=13h or 1 p.m.
 *
 * In the third line with tall lamps every lamp represents 5 minutes.
 * There are 11 lamps, the 3rd, 6th, and 9th are red indicating the first quarter, half, and the last quarter of the hour.
 *
 * In the last line with yellow lamps every lamp represents 1 minute.
 *
 * Possible Values 0 (off) - Y (yellow) for minutes and seconds - R (red) for hours, first, half, last quarter of the hour)
 *
 * TopLamp (value=2secs):       Y/0
 * FirstLine (value=5hours):    R/0 R/0 R/0 R/0
 * SecondLine (value=1hour):    R/0 R/0 R/0 R/0
 * ThirdLine (value=5mins):     Y/0 Y/0 R/0 Y/0 Y/0 R/0 Y/0 Y/0 R/0 Y/0 Y/0
 * ForthLine (value=1min):      Y/0 Y/0 Y/0 Y/0
 *
 */
public class BerlinClockConverterTest {

    private BerlinClockConverter converter;

    @Before
    public void setUp() {
        converter = new BerlinClockConverter();
    }

    @After
    public void tearDown() {
        converter = null;
    }

    @Test(expected = BerlinClockInputException.class)
    public void testBerlinClockConverterWithWrongHoursInputParam() throws BerlinClockInputException {
        converter.berlinClockConverter("", "10", "20");
    }

    @Test(expected = BerlinClockInputException.class)
    public void testBerlinClockConverterWithWrongMinutesInputParam() throws BerlinClockInputException {
        converter.berlinClockConverter("10", "", "20");
    }

    @Test(expected = BerlinClockInputException.class)
    public void testBerlinClockConverterWithWrongSecondsInputParam() throws BerlinClockInputException {
        converter.berlinClockConverter("10", "20", "");
    }

    @Test
    public void testBerlinClockConverterWithLowerEdgeValues() throws BerlinClockInputException {
        String result = converter.berlinClockConverter("0", "0", "0");
        assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", result);
    }

    @Test
    public void testBerlinClockConverterWithLowerEdgeDoubleValues() throws BerlinClockInputException {
        String result = converter.berlinClockConverter("00", "00", "00");
        assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", result);
    }

    @Test(expected = BerlinClockInputException.class)
    public void testBerlinClockConverterWithLowerEdgeTripleValues() throws BerlinClockInputException {
        converter.berlinClockConverter("000", "000", "000");
    }

    @Test
    public void testBerlinClockConverterForValues_12_22_59() throws BerlinClockInputException {
        String result = converter.berlinClockConverter("12", "22", "59");
        assertEquals("O\nRROO\nRROO\nYYRYOOOOOOO\nYYOO", result);
    }

    @Test
    public void testBerlinClockConverterForValues_12_30_30() throws BerlinClockInputException {
        String result = converter.berlinClockConverter("12", "30", "30");
        assertEquals("Y\nRROO\nRROO\nYYRYYROOOOO\nOOOO", result);

    }

    @Test
    public void testBerlinClockConverterForValues_14_49_45() throws BerlinClockInputException {
        String result = converter.berlinClockConverter("14", "49", "45");
        assertEquals("O\nRROO\nRRRR\nYYRYYRYYROO\nYYYY", result);
    }

    @Test
    public void testBerlinClockConverterWithUpperEdgeValues() throws BerlinClockInputException {
        String result = converter.berlinClockConverter("23", "59", "59");
        assertEquals("O\nRRRR\nRRRO\nYYRYYRYYRYY\nYYYY", result);
    }

    /**
     * Single long lamp that shows seconds blinking every to seconds.
     */
    @Test
    public void testTopLampBehaviour() {
        assertEquals(YELLOW.getColor(), converter.getTopLampAsSeconds(0));
        assertEquals(OFF.getColor(), converter.getTopLampAsSeconds(1));
        assertEquals(YELLOW.getColor(), converter.getTopLampAsSeconds(2));
        assertEquals(OFF.getColor(), converter.getTopLampAsSeconds(3));
        assertEquals(YELLOW.getColor(), converter.getTopLampAsSeconds(4));
        assertEquals(OFF.getColor(), converter.getTopLampAsSeconds(5));
        assertEquals(YELLOW.getColor(), converter.getTopLampAsSeconds(6));
        assertEquals(OFF.getColor(), converter.getTopLampAsSeconds(7));
        assertEquals(YELLOW.getColor(), converter.getTopLampAsSeconds(8));
        assertEquals(OFF.getColor(), converter.getTopLampAsSeconds(9));
        assertEquals(YELLOW.getColor(), converter.getTopLampAsSeconds(10));
        assertEquals(OFF.getColor(), converter.getTopLampAsSeconds(59));
    }

    /**
     * First lamps row where each lamp counts 5 hours.
     * All the possible values below.
     */
    @Test
    public void testFirstLineBehaviour() {
        assertEquals("OOOO", converter.getFirstLineLampsAsHours(0));
        assertEquals("ROOO", converter.getFirstLineLampsAsHours(5));
        assertEquals("ROOO", converter.getFirstLineLampsAsHours(9));
        assertEquals("RROO", converter.getFirstLineLampsAsHours(10));
        assertEquals("RROO", converter.getFirstLineLampsAsHours(14));
        assertEquals("RRRO", converter.getFirstLineLampsAsHours(15));
        assertEquals("RRRO", converter.getFirstLineLampsAsHours(19));
        assertEquals("RRRR", converter.getFirstLineLampsAsHours(20));
        assertEquals("RRRR", converter.getFirstLineLampsAsHours(24));
    }

    /**
     * Second lamps row where each lamp counts 1 hour.
     * All the possible values below.
     */
    @Test
    public void testSecondLineBehaviour() {
        assertEquals("OOOO", converter.getSecondLineLampsAsResidualHours(0));
        assertEquals("ROOO", converter.getSecondLineLampsAsResidualHours(1));
        assertEquals("RROO", converter.getSecondLineLampsAsResidualHours(2));
        assertEquals("RRRO", converter.getSecondLineLampsAsResidualHours(3));
        assertEquals("RRRR", converter.getSecondLineLampsAsResidualHours(4));
    }

    /**
     * Third lamps row where each lamp counts 5 minute with different color for first, half and last quarter of hour.
     * All the possible values below.
     */
    @Test
    public void testThirdLineBehaviour() {
        assertEquals("OOOOOOOOOOO", converter.getThirdLineLampsAsMinutes(0));
        assertEquals("YOOOOOOOOOO", converter.getThirdLineLampsAsMinutes(5));
        assertEquals("YYOOOOOOOOO", converter.getThirdLineLampsAsMinutes(10));
        assertEquals("YYROOOOOOOO", converter.getThirdLineLampsAsMinutes(15));
        assertEquals("YYRYOOOOOOO", converter.getThirdLineLampsAsMinutes(20));
        assertEquals("YYRYYOOOOOO", converter.getThirdLineLampsAsMinutes(25));
        assertEquals("YYRYYROOOOO", converter.getThirdLineLampsAsMinutes(30));
        assertEquals("YYRYYRYOOOO", converter.getThirdLineLampsAsMinutes(35));
        assertEquals("YYRYYRYYOOO", converter.getThirdLineLampsAsMinutes(40));
        assertEquals("YYRYYRYYROO", converter.getThirdLineLampsAsMinutes(45));
        assertEquals("YYRYYRYYRYO", converter.getThirdLineLampsAsMinutes(50));
        assertEquals("YYRYYRYYRYY", converter.getThirdLineLampsAsMinutes(55));
    }

    /**
     * Forth lamps row where each lamp counts 1 minute.
     * All the possible values below.
     */
    @Test
    public void testForthLineBehaviour() {
        assertEquals("OOOO", converter.getForthLineLampsAsResidualMinutes(0));
        assertEquals("YOOO", converter.getForthLineLampsAsResidualMinutes(1));
        assertEquals("YYOO", converter.getForthLineLampsAsResidualMinutes(2));
        assertEquals("YYYO", converter.getForthLineLampsAsResidualMinutes(3));
        assertEquals("YYYY", converter.getForthLineLampsAsResidualMinutes(4));
    }

}
