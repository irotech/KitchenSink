package com.gmail.at.irotech.stockmarket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.gmail.at.irotech.stockmarket.StockMarketAnalyser.basePath;
import static org.junit.Assert.*;

public class StockMarketAnalyserTest {

    final String INPUT_FILE_CONTENT =
            "\"EXAMPLE1\",1,2,3,4,5,6\n" +
            "\"EXAMPLE2\",4,2,4,7,1,5\n" +
            "\"EXAMPLE3\",6,5,4,3,2,1";

    final List<String> ANALYSIS_RESULT_LIST = Arrays.asList(
            "EXAMPLE1|1|6|5\n" +
            "EXAMPLE2|2|4|5\n" +
            "EXAMPLE3|0|0|0\n"
    );

    final String DEFAULT_VALUE = "PROD|0|0|0";

    StockMarketAnalyser stockMarketAnalyser;

    @Before
    public void setUp() throws Exception {
        stockMarketAnalyser = new StockMarketAnalyser();
    }

    @After
    public void tearDown() throws Exception {
        stockMarketAnalyser = null;
    }

    @Test
    public void fileReader() throws Exception {
        Path path = Files.createTempFile(basePath, "inputTest", ".csv");
        File file = path.toFile();
        Files.write(path, INPUT_FILE_CONTENT.getBytes(StandardCharsets.UTF_8));
        file.deleteOnExit();
        final Map<String, String[]> returnMap = stockMarketAnalyser.fileReader(file.getName());
        assertNotNull(returnMap);
        assertEquals(Arrays.asList("1", "2", "3", "4", "5", "6"), Arrays.asList(returnMap.get("\"EXAMPLE1\"")));
        assertEquals(Arrays.asList("4", "2", "4", "7", "1", "5"), Arrays.asList(returnMap.get("\"EXAMPLE2\"")));
        assertEquals(Arrays.asList("6", "5", "4", "3", "2", "1"), Arrays.asList(returnMap.get("\"EXAMPLE3\"")));
    }

    @Test
    public void analyseProductWithInsufficientMarketValues() throws Exception {
        final String retVal = stockMarketAnalyser.analyseProduct("PROD", new String[]{"1"});
        assertEquals("PROD|insufficient-market-values", retVal);
    }

    @Test
    public void analyseProductWithOnlyTwoMarketValuesWithoutProfit() throws Exception {
        final String retVal = stockMarketAnalyser.analyseProduct("PROD", new String[]{"1","3"});
        assertEquals("PROD|1|3|2|", retVal);
    }

    @Test
    public void analyseProductWithOnlyTwoMarketValuesWithProfit() throws Exception {
        final String retVal = stockMarketAnalyser.analyseProduct("PROD", new String[]{"3","1"});
        assertEquals(DEFAULT_VALUE, retVal);
    }

    @Test
    public void analyseProductAsPerRequirementExample1() throws Exception {
        final String retVal = stockMarketAnalyser.analyseProduct("PROD", new String[]{"1","2","3","4","5","6"});
        assertEquals("PROD|1|6|5", retVal);
    }

    @Test
    public void analyseProductAsPerRequirementExample2() throws Exception {
        final String retVal = stockMarketAnalyser.analyseProduct("PROD", new String[]{"4","2","4","7","1","5"});
        assertEquals("PROD|2|4|5", retVal);
    }

    @Test
    public void analyseProductAsPerRequirementExample3() throws Exception {
        final String retVal = stockMarketAnalyser.analyseProduct("PROD", new String[]{"6","5","4","3","2","1"});
        assertEquals(DEFAULT_VALUE, retVal);
    }

    @Test
    public void analyseProductWithOneInvalidValue() throws Exception {
        final String retVal = stockMarketAnalyser.analyseProduct("PROD", new String[]{"1","x","3"});
        assertEquals("PROD|insufficient-market-values", retVal);
    }

    @Test
    public void analyseProductWithAllInvalidValues() throws Exception {
        final String retVal = stockMarketAnalyser.analyseProduct("PROD", new String[]{"x","y","z"});
        assertEquals(DEFAULT_VALUE, retVal);
    }

    //... more scenarios here

    @Test
    public void fileWriter() throws Exception {
        final String outputFileName = "outputTest.csv";
        stockMarketAnalyser.fileWriter(outputFileName, ANALYSIS_RESULT_LIST);
        final File outputTestFile = new File(String.valueOf(basePath), outputFileName);
        final Stream<String> lines = Files.lines(outputTestFile.toPath());
        outputTestFile.delete();
        assertNotNull(lines);
        assertTrue(lines.count()==4);
    }

}
