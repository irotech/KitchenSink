package com.gmail.at.irotech.math.spreadsheet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExcelCellDecoderTest {

    ExcelCellDecoder decoder;

    final int BASE_INTERVAL = 26;
    final int interval = 702;

    @Before
    public void setUp() {
        decoder = new ExcelCellDecoder();
    }

    @After
    public void tearDown() {
        decoder = null;
    }

    @Test
    public void testDecodeCellRow1Column12() throws Exception {
        final String s = decoder.decodeCell(12);
        assertEquals("0L", s);
    }

    @Test
    public void testDecodeCellRow2Column12() throws Exception {
        final String s = decoder.decodeCell(interval+12);
        assertEquals("1L", s);
    }

    @Test
    public void testDecodeCellRow3Column12() throws Exception {
        final String s = decoder.decodeCell(interval*3+12);
        assertEquals("3L", s);
    }

    @Test
    public void testDecodeCellRow10Column12() throws Exception {
        final String s = decoder.decodeCell(interval*10+12);
        assertEquals("10L", s);
    }

    @Test
    public void testDecodeCellRow300Column12() throws Exception {
        final String s = decoder.decodeCell(interval*300+12);
        assertEquals("300L", s);
    }

    @Test
    public void testDecodeCellRow3Group2Column12() throws Exception {
        final String s = decoder.decodeCell((interval*3)+BASE_INTERVAL+12);
        assertEquals("3AL", s);
    }

    @Test
    public void testDecodeCellRow3Group3Column12() throws Exception {
        final String s = decoder.decodeCell((interval*3)+(BASE_INTERVAL*3)+12);
        assertEquals("3CL", s);
    }

    @Test
    public void testDecodeCellRow3Group5Column12() throws Exception {
        final String s = decoder.decodeCell((interval*3)+(BASE_INTERVAL*5)+12);
        assertEquals("3EL", s);
    }

    @Test
    public void testDecodeCellRow3Group10Column12() throws Exception {
        final String s = decoder.decodeCell((interval*3)+(BASE_INTERVAL*10)+12);
        assertEquals("3JL", s);
    }

    @Test
    public void testDecodeCellRow3Group25Column12() throws Exception {
        final String s = decoder.decodeCell((interval*3)+(BASE_INTERVAL*25)+12);
        assertEquals("3YL", s);
    }

    @Test
    public void testDecodeCellRow3Group26Column12() throws Exception {
        final String s = decoder.decodeCell((interval*3)+(BASE_INTERVAL*26)+12);
        assertEquals("3ZL", s);
    }

    @Test
    public void testDecodeCellRow3Group26Column26() throws Exception {
        final String s = decoder.decodeCell((interval*3)+(BASE_INTERVAL*26)+26);
        assertEquals("3ZZ", s);
    }

}