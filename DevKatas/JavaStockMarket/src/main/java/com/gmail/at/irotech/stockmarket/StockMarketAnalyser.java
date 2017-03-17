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
    final String INSUFFICIENT_VALUES = "insufficient-market-values";

    void analyse(String inputFileName, String outputFileName) {
        System.out.println("Analysing Stock Market from the source " + basePath.toString() + File.separator + (inputFileName));
        try {
            final Map<String, String[]> stringMap = fileReader(inputFileName);
            List<String> marketAnalysisResults = new ArrayList<>();
            stringMap.forEach((k, v) -> marketAnalysisResults.add(analyseProduct(k, v)));
            fileWriter(outputFileName, marketAnalysisResults);
            System.out.println("Stock Market Analysis produced. Please check " + basePath.toString() + File.separator + outputFileName);
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
        StringBuilder analysisBuilder = new StringBuilder(productName.replace("\"", "")).append(OUTPUT_SEPARATOR);
        if(marketValues.length < 2) {
            return analysisBuilder.append(INSUFFICIENT_VALUES).toString();
        }

        int buy = 0;
        int sell = 0;
        int profit = 0;

        int minVal = 0;
        int minIndex = 0;

        Map<Integer, Integer[]> marketOperationsMap = new HashMap<>();

        for(int i = 0; i < marketValues.length; i++) {
            Integer currentVal = 0;
            try {
                currentVal = Integer.parseInt(marketValues[i]);
            } catch (NumberFormatException e) {
                continue;
            }
            int currentProfit = 0;
            if(i == 0) {
                minVal = currentVal.intValue();
            } else {
                if(currentVal.intValue() > minVal) {
                    currentProfit = currentVal.intValue() - minVal;
                    if(currentProfit > profit) {
                        profit = currentProfit;
                        marketOperationsMap.put(currentProfit, new Integer[] {(minIndex==0)?1:minIndex, i+1});
                    }
                } else {
                    minVal = currentVal.intValue();
                    minIndex = i + 1;
                }
            }
        }

        profit = marketOperationsMap.keySet().stream().reduce(Integer::max).orElse(0).intValue();
        final Integer[] indexes = marketOperationsMap.get(profit);
        if(null != indexes && indexes.length > 0) {
            buy = indexes[0].intValue();
            sell = indexes[1].intValue();
        }
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
