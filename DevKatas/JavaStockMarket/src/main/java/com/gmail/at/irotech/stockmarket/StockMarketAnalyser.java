package com.gmail.at.irotech.stockmarket;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class StockMarketAnalyser {

    final static Path basePath = FileSystems.getDefault().getPath(System.getProperty("user.home"), "downloads");

    final String INPUT_SEPARATOR = ",";
    final String OUTPUT_SEPARATOR = "|";

    void analyse(String inputFileName, String outputFileName) {
        System.out.println("Analysing Stock Market from the source " + inputFileName);
        try {
            final Map<String, String[]> stringMap = fileReader(inputFileName);
            List<String> marketAnalysisResults = new ArrayList<>();
            stringMap.forEach((k, v) -> marketAnalysisResults.add(analyseProduct(k, v)));
            fileWriter(outputFileName, marketAnalysisResults);
            System.out.println("Stock Market Analysis produced. Please check the " + outputFileName + " file out.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Map<String, String[]> fileReader(String inputFileName) throws IOException {
        Map<String, String[]> retMap = new HashMap<>();
        Files.lines(new File(String.valueOf(basePath), inputFileName).toPath()).forEach(line -> {
            final String[] split = line.split(INPUT_SEPARATOR);
            Optional.ofNullable(split).ifPresent(array ->
                retMap.put(split[0], Arrays.copyOfRange(split, 1, split.length))
            );
        });
        return retMap;
    }

    String analyseProduct(String productName, String... marketValues) {
        StringBuilder analysisBuilder = new StringBuilder(productName).append(OUTPUT_SEPARATOR);
        int buy = 0;
        int sell = 0;
        int profit = 0;

        Arrays.stream(marketValues).forEach(val -> {
            
        });

        analysisBuilder.append(String.valueOf(buy)).append(OUTPUT_SEPARATOR);
        analysisBuilder.append(String.valueOf(sell)).append(OUTPUT_SEPARATOR);
        analysisBuilder.append(String.valueOf(profit));
        return analysisBuilder.toString();
    }

    void fileWriter(String outputFileName, List<String> values) throws IOException {
        StringBuilder outputBuilder = new StringBuilder();
        values.stream().forEach(val -> outputBuilder.append(val).append(System.lineSeparator()));
        Files.write(Paths.get(String.valueOf(basePath), outputFileName), outputBuilder.toString().getBytes(Charset.defaultCharset()));
    }

    public static void main(String... args) {
        System.out.println("Start msg ...");
        args = new String[] { "input.csv", "output.csv" };
        if(args.length < 2) {
            System.out.println("Please make sure to add the input and output files name as parameters.");
        } else {
            StockMarketAnalyser stockMarketAnalyser = new StockMarketAnalyser();
            stockMarketAnalyser.analyse(args[0], args[1]);
        }
        System.out.println("Stop msg ...");
    }

}
