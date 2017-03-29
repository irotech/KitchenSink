package com.gmail.at.irotech.math.matrix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiagonalDifferenceTest {

    private DiagonalDifference diagonalDiff;

    private int[][] matrix;

    @Before
    public void setUp() throws Exception {
        diagonalDiff = new DiagonalDifference();
    }

    @After
    public void tearDown() throws Exception {
        diagonalDiff = null;
        matrix = null;
    }

    /**
     * 11   2   4
     * 4    5   6
     * 10   8   -12
     */
    @Test
    public void calculateMatrixDiagonalDiff() throws Exception {
        matrix = new int[][] {{11, 2, 4}, {4, 5, 6}, {10, 8, -12}};
        assertEquals(23, diagonalDiff.calculateMatrixDiagonalDiff(3, matrix));
    }

}