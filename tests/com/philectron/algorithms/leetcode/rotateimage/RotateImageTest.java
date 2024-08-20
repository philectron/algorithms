package com.philectron.algorithms.leetcode.rotateimage;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import org.junit.jupiter.api.Test;

public class RotateImageTest {

    private static RotateImage solution = new RotateImage();

    @Test
    public void rotateClockwise_0x0() {
        final int n = 0;
        int[][] expected = new int[0][0];
        assertSquareMatrix(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_0x0() {
        final int n = 0;
        int[][] expected = new int[0][0];
        assertSquareMatrix(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwise_1x1() {
        final int n = 1;
        int[][] expected = { { 1 } };
        assertSquareMatrix(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_1x1() {
        final int n = 1;
        int[][] expected = { { 1 } };
        assertSquareMatrix(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwise_2x2() {
        final int n = 2;
        int[][] expected = {
            { 3, 1 },
            { 4, 2 }
        };
        assertSquareMatrix(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_2x2() {
        final int n = 2;
        int[][] expected = {
            { 1, 2 },
            { 3, 4 }
        };
        assertSquareMatrix(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwise_3x3() {
        final int n = 3;
        int[][] expected = {
            { 7, 4, 1 },
            { 8, 5, 2 },
            { 9, 6, 3 }
        };
        assertSquareMatrix(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_3x3() {
        final int n = 3;
        int[][] expected = {
            { 1, 4, 3 },
            { 8, 5, 2 },
            { 7, 6, 9 }
        };
        assertSquareMatrix(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwise_4x4() {
        final int n = 4;
        int[][] expected = {
            { 13, 9, 5, 1 },
            { 14, 10, 6, 2 },
            { 15, 11, 7, 3 },
            { 16, 12, 8, 4 }
        };
        assertSquareMatrix(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_4x4() {
        final int n = 4;
        int[][] expected = {
            { 1, 9, 5, 4 },
            { 14, 6, 7, 2 },
            { 15, 10, 11, 3 },
            { 13, 12, 8, 16 }
        };
        assertSquareMatrix(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwise_5x5() {
        final int n = 5;
        int[][] expected = {
            { 21, 16, 11, 6, 1 },
            { 22, 17, 12, 7, 2 },
            { 23, 18, 13, 8, 3 },
            { 24, 19, 14, 9, 4 },
            { 25, 20, 15, 10, 5 }
        };
        assertSquareMatrix(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_5x5() {
        final int n = 5;
        int[][] expected = {
            { 1, 16, 11, 6, 5 },
            { 22, 7, 12, 9, 2 },
            { 23, 18, 13, 8, 3 },
            { 24, 17, 14, 19, 4 },
            { 21, 20, 15, 10, 25 }
        };
        assertSquareMatrix(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwise_6x6() {
        final int n = 6;
        int[][] expected = {
            { 31, 25, 19, 13, 7, 1 },
            { 32, 26, 20, 14, 8, 2 },
            { 33, 27, 21, 15, 9, 3 },
            { 34, 28, 22, 16, 10, 4 },
            { 35, 29, 23, 17, 11, 5 },
            { 36, 30, 24, 18, 12, 6 }
        };
        assertSquareMatrix(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_6x6() {
        final int n = 6;
        int[][] expected = {
            { 1, 25, 19, 13, 7, 6 },
            { 32, 8, 20, 14, 11, 2 },
            { 33, 27, 15, 16, 9, 3 },
            { 34, 28, 21, 22, 10, 4 },
            { 35, 26, 23, 17, 29, 5 },
            { 31, 30, 24, 18, 12, 36 }
        };
        assertSquareMatrix(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwise_7x7() {
        final int n = 7;
        int[][] expected = {
            { 43, 36, 29, 22, 15, 8, 1 },
            { 44, 37, 30, 23, 16, 9, 2 },
            { 45, 38, 31, 24, 17, 10, 3 },
            { 46, 39, 32, 25, 18, 11, 4 },
            { 47, 40, 33, 26, 19, 12, 5 },
            { 48, 41, 34, 27, 20, 13, 6 },
            { 49, 42, 35, 28, 21, 14, 7 }
        };
        assertSquareMatrix(solution.rotateClockwise(buildSquareMatrix(n)), expected);
    }

    @Test
    public void rotateClockwiseExceptDiagonals_7x7() {
        final int n = 7;
        int[][] expected = {
            { 1, 36, 29, 22, 15, 8, 7 },
            { 44, 9, 30, 23, 16, 13, 2 },
            { 45, 38, 17, 24, 19, 10, 3 },
            { 46, 39, 32, 25, 18, 11, 4 },
            { 47, 40, 31, 26, 33, 12, 5 },
            { 48, 37, 34, 27, 20, 41, 6 },
            { 43, 42, 35, 28, 21, 14, 49 }
        };
        assertSquareMatrix(solution.rotateClockwiseExceptDiagonals(buildSquareMatrix(n)), expected);
    }

    private void assertSquareMatrix(int[][] actual, int[][] expected) {
        if (expected.length == 0) {
            assertThat(actual).isEmpty();
            return;
        }

        String actualStr = matrixToString(actual);
        String expectedStr = matrixToString(expected);

        assertWithMessage(printMatrix(actualStr, expectedStr) + "Row size")
                .that(actual.length)
                .isEqualTo(expected.length);

        for (int i = 0, n = actual.length; i < n; i++) {
            assertWithMessage(printMatrix(actualStr, expectedStr) + "Column size on row %s", i)
                    .that(actual[i])
                    .hasLength(expected[i].length);
        }

        for (int i = 0, n = actual.length; i < n; i++) {
            for (int j = 0; j < n; j++) {
                assertWithMessage(printMatrix(actualStr, expectedStr) + "Value at [%s][%s]", i, j)
                        .that(actual[i][j])
                        .isEqualTo(expected[i][j]);
            }
        }
    }

    private String printMatrix(String actualStr, String expectedStr) {
        return String.format("Expected matrix:\n%sActual matrix:\n%s", expectedStr, actualStr);
    }

    private String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int value : row) {
                sb.append(value + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int[][] buildSquareMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0, value = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = value++;
            }
        }
        return matrix;
    }

}
