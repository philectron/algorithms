package com.philectron.algorithms.leetcode.rotateimage;

/**
 * @see https://leetcode.com/problems/rotate-image
 */
public class RotateImage {

    public int[][] rotateClockwise(final int[][] matrix) {
        for (int i = 0, n = matrix.length; i < n; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                rotateCellsClockwise(matrix, i, j);
            }
        }
        return matrix;
    }

    public int[][] rotateClockwiseExceptDiagonals(final int[][] matrix) {
        // This is a much shorter solution compared to using 4 different indices.
        // Loop over the top quarter only since we can get the indices of the corresponding cells
        // of the other quarters by rotating the top indices.
        for (int i = 0, n = matrix.length; i < n; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                // Skip if the cell is on the principal diagonal.
                if (i == j) {
                    continue;
                }
                rotateCellsClockwise(matrix, i, j);
            }
        }

        return matrix;
    }

    private void rotateCellsClockwise(final int[][] matrix, final int i, final int j) {
        final int n = matrix.length;
        // Temporarily save the top quarter's value.
        final int tmp = matrix[i][j];
        // Rotate left to top.
        matrix[i][j] = matrix[n - 1 - j][i];
        // Rotate bottom to left.
        matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
        // Rotate right to bottom.
        matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
        // Rotate top to right.
        matrix[j][n - 1 - i] = tmp;
    }

}
