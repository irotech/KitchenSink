package com.gmail.at.irotech.math.spreadsheet;

import java.util.Scanner;

public class ExcelCellDecoder {

    final String[] chars = new String[] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    final int n = 26;

    String decodeCell(int cellNumber) {
        if(cellNumber > 0) {
            int rows = 0;

            int excelRowElems = (n * (n + 1));

            final float rowNum = cellNumber / excelRowElems;
            final float rowNumRemainder = cellNumber % excelRowElems;

            if(cellNumber>excelRowElems && rowNumRemainder > 0.0) {
                rows = Math.round(rowNum) + 1;
            } else {
                rows = Math.round(rowNum);
            }

            int cols = cellNumber - (excelRowElems * ((rows==0)?0:--rows));

            if(cols<n) {
                return String.valueOf(rows) + chars[cols-1];
            } else {
                String first = null;
                String second = null;
                if(cols<excelRowElems) {
                    first = chars[(cols/n)-1];
                    second = chars[(cols-(cols/n*n)-1)];
                } else {
                    first = chars[(cols/n)-2];
                    second = chars[((cols/n)-2)];
                }
                return String.valueOf(rows) + first + second;
            }
        }
        return null;
    }

    public static void main(String... args) {

        System.out.println("Please insert the the number you want to decode as cell: ");
        Scanner scanner = new Scanner(System.in);
        final int input = scanner.nextInt();
        scanner.close();
        System.out.println("Registered input: " + input);

        ExcelCellDecoder exd = new ExcelCellDecoder();
        final String dc = exd.decodeCell(input);
        System.out.println("Cell location: " + dc);

    }

}
