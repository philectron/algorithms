package com.philectron.algorithms.logic;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;

public final class Assertion {

    private Assertion() {}

    /**
     * Wraps {@link Verify#verifyNotNull(Object)} to throw {@link AssertionError} on failure.
     *
     * <p>
     * This method is different from {@link Verify#verifyNotNull(Object)} in that it classifies the
     * cause of failure as impossible conditions in the code (assertion failure) rather than by
     * verification failures.
     * </p>
     *
     * @param <T> any object type
     * @param reference the reference to be null-checked
     *
     * @return {@code reference}, guaranteed to be non-null, for convenience
     *
     * @throws AssertionError if {@code reference} is {@code null}
     *
     * @see https://github.com/google/guava/wiki/ConditionalFailuresExplained
     */
    public static <T> T assertNotNull(T reference) {
        try {
            return Verify.verifyNotNull(reference);
        } catch (VerifyException e) {
            throw new AssertionError(e.getMessage());
        }
    }

    /**
     * Wraps {@link Preconditions#checkElementIndex(int, int)} to throw {@link AssertionError} on
     * failure.
     *
     * <p>
     * This method is different from {@link Preconditions#checkElementIndex(int, int)} in that it
     * classifies the cause of failure as impossible conditions in the code (assertion failure)
     * rather than by precondition failures.
     * </p>
     *
     * @param index a user-supplied index identifying an element of an array, list or string
     * @param size the size of that array, list or string
     *
     * @return the value of {@code index}, for convenience
     *
     * @throws AssertionError if {@code index} is negative or is not less than {@code size}, or if
     *         {@code size} is negative
     */
    public static int assertElementIndex(int index, int size) {
        try {
            return Preconditions.checkElementIndex(index, size);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new AssertionError(e.getMessage());
        }
    }

}
