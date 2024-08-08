package com.philectron.algorithms.leetcode.rotateimage;

/**
 * @see https://leetcode.com/problems/rotate-image
 */
public class RotateImage {

    public int[][] rotateExceptDiagonals(final int[][] matrix) {
        final int n = matrix.length;

        // Use 4 cursors, one for each quarter.
        int ti = 0, tj = 1;
        int ri = 1, rj = n - 1;
        int bi = n - 1, bj = n - 2;
        int li = n - 2, lj = 0;
        while ((ti < tj && ti < n - tj - 1) && (ri < rj && rj > n - rj - 1)
                && (bi > bj && bi > n - bj - 1) && (li > lj && li < n - lj - 1)) {
            // Swap top and right -> top is in the correct place (right).
            swap(matrix, ti, tj, ri, rj);
            // Swap new top (i.e. original right) and bottom -> right is in the correct place (bottom).
            swap(matrix, ti, tj, bi, bj);
            // Swap new top (i.e. original bottom) and left -> bottom is in the correct place (left).
            // Left is then also in the correct place (top).
            swap(matrix, ti, tj, li, lj);

            // Top cursor moves right. If hit the secondary diagonal, move toward the center (down).
            tj++;
            if (ti == n - tj - 1) {
                ti++;
                tj = ti + 1;
            }

            // Right cursor moves down. If hit the principal diagonal, move toward the center (left).
            ri++;
            if (ri == rj) {
                rj--;
                ri = n - rj;
            }

            // Bottom cursor moves left. If hit the secondary diagonal, move toward the center (up).
            bj--;
            if (bi == n - bj - 1) {
                bi--;
                bj = bi - 1;
            }

            // Left cursor moves up. If hit the principal diagonal, move toward the center (right).
            li--;
            if (li == lj) {
                lj++;
                li = n - lj - 2;
            }
        }

        return matrix;
    }

    private void swap(final int[][] matrix, final int i, final int j, final int k, final int l) {
        final int tmp = matrix[i][j];
        matrix[i][j] = matrix[k][l];
        matrix[k][l] = tmp;
    }

}
