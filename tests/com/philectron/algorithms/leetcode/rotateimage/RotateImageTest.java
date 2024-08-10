package com.philectron.algorithms.leetcode.rotateimage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class RotateImageTest {

    private static final RotateImage solution = new RotateImage();

    @Test
    public void rotateClockwise_0x0() {
        final int n = 0;
        final int[][] expected = new int[0][0];
        assertSquareMatricesEquals(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_0x0() {
        final int n = 0;
        final int[][] expected = new int[0][0];
        assertSquareMatricesEquals(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)),
                expected);
    }

    @Test
    public void rotateClockwise_1x1() {
        final int n = 1;
        final int[][] expected = { { 1 } };
        assertSquareMatricesEquals(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_1x1() {
        final int n = 1;
        final int[][] expected = { { 1 } };
        assertSquareMatricesEquals(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)),
                expected);
    }

    @Test
    public void rotateClockwise_2x2() {
        final int n = 2;
        final int[][] expected = {
            { 3, 1 },
            { 4, 2 }
        };
        assertSquareMatricesEquals(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_2x2() {
        final int n = 2;
        final int[][] expected = {
            { 1, 2 },
            { 3, 4 }
        };
        assertSquareMatricesEquals(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)),
                expected);
    }

    @Test
    public void rotateClockwise_3x3() {
        final int n = 3;
        final int[][] expected = {
            { 7, 4, 1 },
            { 8, 5, 2 },
            { 9, 6, 3 }
        };
        assertSquareMatricesEquals(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_3x3() {
        final int n = 3;
        final int[][] expected = {
            { 1, 4, 3 },
            { 8, 5, 2 },
            { 7, 6, 9 }
        };
        assertSquareMatricesEquals(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)),
                expected);
    }

    @Test
    public void rotateClockwise_4x4() {
        final int n = 4;
        final int[][] expected = {
            { 13, 9, 5, 1 },
            { 14, 10, 6, 2 },
            { 15, 11, 7, 3 },
            { 16, 12, 8, 4 } };
        assertSquareMatricesEquals(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_4x4() {
        final int n = 4;
        final int[][] expected = {
            { 1, 9, 5, 4 },
            { 14, 6, 7, 2 },
            { 15, 10, 11, 3 },
            { 13, 12, 8, 16 }
        };
        assertSquareMatricesEquals(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)),
                expected);
    }

    @Test
    public void rotateClockwise_5x5() {
        final int n = 5;
        final int[][] expected = {
            { 21, 16, 11, 6, 1 },
            { 22, 17, 12, 7, 2 },
            { 23, 18, 13, 8, 3 },
            { 24, 19, 14, 9, 4 },
            { 25, 20, 15, 10, 5 }
        };
        assertSquareMatricesEquals(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_5x5() {
        final int n = 5;
        final int[][] expected = {
            { 1, 16, 11, 6, 5 },
            { 22, 7, 12, 9, 2 },
            { 23, 18, 13, 8, 3 },
            { 24, 17, 14, 19, 4 },
            { 21, 20, 15, 10, 25 }
        };
        assertSquareMatricesEquals(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)),
                expected);
    }

    @Test
    public void rotateClockwise_6x6() {
        final int n = 6;
        final int[][] expected = {
            { 31, 25, 19, 13, 7, 1 },
            { 32, 26, 20, 14, 8, 2 },
            { 33, 27, 21, 15, 9, 3 },
            { 34, 28, 22, 16, 10, 4 },
            { 35, 29, 23, 17, 11, 5 },
            { 36, 30, 24, 18, 12, 6 }
        };
        assertSquareMatricesEquals(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_6x6() {
        final int n = 6;
        final int[][] expected = {
            { 1, 25, 19, 13, 7, 6 },
            { 32, 8, 20, 14, 11, 2 },
            { 33, 27, 15, 16, 9, 3 },
            { 34, 28, 21, 22, 10, 4 },
            { 35, 26, 23, 17, 29, 5 },
            { 31, 30, 24, 18, 12, 36 }
        };
        assertSquareMatricesEquals(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)),
                expected);
    }

    @Test
    public void rotateClockwise_7x7() {
        final int n = 7;
        final int[][] expected = {
            { 43, 36, 29, 22, 15, 8, 1 },
            { 44, 37, 30, 23, 16, 9, 2 },
            { 45, 38, 31, 24, 17, 10, 3 },
            { 46, 39, 32, 25, 18, 11, 4 },
            { 47, 40, 33, 26, 19, 12, 5 },
            { 48, 41, 34, 27, 20, 13, 6 },
            { 49, 42, 35, 28, 21, 14, 7 }
        };
        assertSquareMatricesEquals(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_7x7() {
        final int n = 7;
        final int[][] expected = {
            { 1, 36, 29, 22, 15, 8, 7 },
            { 44, 9, 30, 23, 16, 13, 2 },
            { 45, 38, 17, 24, 19, 10, 3 },
            { 46, 39, 32, 25, 18, 11, 4 },
            { 47, 40, 31, 26, 33, 12, 5 },
            { 48, 37, 34, 27, 20, 41, 6 },
            { 43, 42, 35, 28, 21, 14, 49 }
        };
        assertSquareMatricesEquals(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)),
                expected);
    }

    private void assertSquareMatricesEquals(final int[][] actual, final int[][] expected) {
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
                        "Matrices must equal:\nExpected matrix:\n%sBut was:\n%sDiffer at index [%d][%d]",
                        expectedStr, actualStr, i, j), actual[i][j], is(expected[i][j]));
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

    private int[][] buildSquareMatrix(final int size) {
        final int[][] matrix = new int[size][size];
        for (int i = 0, value = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = value++;
            }
        }
        return matrix;
    }

}
