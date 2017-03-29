package com.gmail.at.irotech.math.matrix;

import java.util.Scanner;

/**
 * Given a square matrix of size NxN, calculate the absolute difference between the sums of its diagonals.
 *
 * Input Format
 * The first line contains a single integer, N. The next N lines denote the matrix's rows, with each line
 * containing space-separated integers describing the columns.
 *
 * Output Format
 * Print the absolute difference between the two sums of the matrix's diagonals as a single integer.
 *
 * Sample Input
 * 3
 * 11 2 4
 * 4 5 6
 * 10 8 -12
 *
 * Sample Output
 * 15 (primary diagonal: 11 + 5 - 12 = 4, secondary diagonal: 4 + 5 + 10 = 19, difference: |4 - 19| = 15)
 */
public class DiagonalDifference {

    protected int calculateMatrixDiagonalDiff(int matrixSize, int[][] matrix) {
        int primary = 0, secondary = 0;
        for(int i=0; i<matrixSize; i++) {
            //calculate primary diagonal
            primary += matrix[i][i];
            //calculate secondary diagonal
            secondary += matrix[(matrixSize-1)-i][i];
        }
        return primary+secondary;
    }

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        final int matrixSize = scanner.nextInt();
        int[][] matrix = new int[matrixSize][matrixSize];
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            for (int i = 0; i < matrixSize; i++) {
                final String[] cells = line.split(" ");
                for (int j = 0; j < matrixSize; j++) {
                    matrix[i][j] = Integer.parseInt(cells[j]);
                }
            }
        }
        System.out.println(new DiagonalDifference().calculateMatrixDiagonalDiff(matrixSize, matrix));
    }

}
