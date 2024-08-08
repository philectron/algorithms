package com.philectron.algorithms.leetcode.rotateimage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class RotateImageTest {

    private static final RotateImage solution = new RotateImage();

    @Test
    public void rotateExceptDiagonals_0x0() {
        final int[][] actual = solution.rotateExceptDiagonals(buildSquareMatrix(0));
        assertSquareMatricesEqual(actual, new int[0][0]);
    }

    @Test
    public void rotateExceptDiagonals_1x1() {
        final int[][] actual = solution.rotateExceptDiagonals(buildSquareMatrix(1));
        final int[][] expected = { { 1 } };
        assertSquareMatricesEqual(actual, expected);
    }

    @Test
    public void rotateExceptDiagonals_2x2() {
        final int[][] actual = solution.rotateExceptDiagonals(buildSquareMatrix(2));
        final int[][] expected = {
            { 1, 2 },
            { 3, 4 }
        };
        assertSquareMatricesEqual(actual, expected);
    }

    @Test
    public void rotateExceptDiagonals_3x3() {
        final int[][] actual = solution.rotateExceptDiagonals(buildSquareMatrix(3));
        final int[][] expected = {
            { 1, 4, 3 },
            { 8, 5, 2 },
            { 7, 6, 9 }
        };
        assertSquareMatricesEqual(actual, expected);
    }

    @Test
    public void rotateExceptDiagonals_4x4() {
        final int[][] actual = solution.rotateExceptDiagonals(buildSquareMatrix(4));
        final int[][] expected = {
            { 1, 9, 5, 4 },
            { 14, 6, 7, 2 },
            { 15, 10, 11, 3 },
            { 13, 12, 8, 16 }
        };
        assertSquareMatricesEqual(actual, expected);
    }

    @Test
    public void rotateExceptDiagonals_5x5() {
        final int[][] actual = solution.rotateExceptDiagonals(buildSquareMatrix(5));
        final int[][] expected = {
            { 1, 16, 11, 6, 5 },
            { 22, 7, 12, 9, 2 },
            { 23, 18, 13, 8, 3 },
            { 24, 17, 14, 19, 4 },
            { 21, 20, 15, 10, 25 }
        };
        assertSquareMatricesEqual(actual, expected);
    }

    @Test
    public void rotateExceptDiagonals_6x6() {
        final int[][] actual = solution.rotateExceptDiagonals(buildSquareMatrix(6));
        final int[][] expected = {
            { 1, 25, 19, 13, 7, 6 },
            { 32, 8, 20, 14, 11, 2 },
            { 33, 27, 15, 16, 9, 3 },
            { 34, 28, 21, 22, 10, 4 },
            { 35, 26, 23, 17, 29, 5 },
            { 31, 30, 24, 18, 12, 36 }
        };
        assertSquareMatricesEqual(actual, expected);
    }

    @Test
    public void rotateExceptDiagonals_7x7() {
        final int[][] actual = solution.rotateExceptDiagonals(buildSquareMatrix(7));
        final int[][] expected = {
            { 1, 36, 29, 22, 15, 8, 7 },
            { 44, 9, 30, 23, 16, 13, 2 },
            { 45, 38, 17, 24, 19, 10, 3 },
            { 46, 39, 32, 25, 18, 11, 4 },
            { 47, 40, 31, 26, 33, 12, 5 },
            { 48, 37, 34, 27, 20, 41, 6 },
            { 43, 42, 35, 28, 21, 14, 49 }
        };
        assertSquareMatricesEqual(actual, expected);
    }

    private int[][] buildSquareMatrix(final int size) {
        final int[][] matrix = new int[size][size];
        for (int i = 0, value = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = value++;
            }
        }
        return matrix;
    }

    private void assertSquareMatricesEqual(final int[][] actual, final int[][] expected) {
        if (expected.length == 0) {
            assertThat("Matrix must be empty", actual.length, is(0));
            return;
        }

        final String actualStr = matrixToString(actual);
        final String expectedStr = matrixToString(expected);

        assertThat("Matrix must be square", actual.length, is(actual[0].length));
        assertThat("Matrices must have the same number of rows", actual.length,
                is(expected.length));
        assertThat("Matrices must have the same number of columns", actual[0].length,
                is(expected[0].length));

        for (int i = 0, n = actual.length; i < n; i++) {
            for (int j = 0; j < n; j++) {
                assertThat(String.format(
                        "Matrices must equal:\nActual matrix:\n%sExpected matrix:\n%sBut differ at index [%d][%d]",
                        actualStr, expectedStr, i, j), actual[i][j], is(expected[i][j]));
            }
        }
    }

    private String matrixToString(final int[][] matrix) {
        final StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int value : row) {
                sb.append(value + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
