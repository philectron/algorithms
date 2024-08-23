package com.philectron.algorithms.logic;

public final class Assertion {

    private Assertion() {}

    /**
     * Ensures that {@code reference} is non-null.
     *
     * @param <T> any object type
     * @param reference the reference to be null-checked
     *
     * @return {@code reference}, guaranteed to be non-null, for convenience
     *
     * @throws AssertionError if {@code reference} is {@code null}
     */
    public static <T> T assertNotNull(T reference) {
        assert reference != null;
        return reference;
    }

    /**
     * Ensures that {@code index} specifies a valid <i>element</i> in an array, list, or string of
     * size {@code size}. An element index may range from zero, inclusive, to {@code size},
     * exclusive.
     *
     * @param index the index identifying an element of an array, list, or string
     * @param size the size of that array, list, or string
     *
     * @return the value of {@code index}, for convenience
     *
     * @throws AssertionError if {@code size} is negative, or if {@code index} is negative or is not
     *         less than {@code size}
     */
    public static int assertElementIndex(int index, int size) {
        assert size >= 0;
        assert 0 <= index && index < size;
        return index;
    }

    /**
     * Ensures that {@code start} and {@code end} specify valid <i>elements</i> in an array, list,
     * or string of size {@code size}, and are in order (can be equal). An element index may range
     * from zero, inclusive, to {@code size}, exclusive.
     *
     * @param start the index identifying a starting element in an array, list, or string
     * @param end the index identifying an ending element in an array, list, or string
     * @param size the size of that array, list, or string
     *
     * @throws AssertionError if {@link #assertElementIndex(start, size)} fails, or if
     *         {@link #assertElementIndex(end, size)} fails, or if {@code start} is greater than
     *         {@code end}
     *
     * @see #assertElementIndex(int, int)
     */
    public static void assertElementIndexes(int start, int end, int size) {
        assertElementIndex(start, size);
        assertElementIndex(end, size);
        assert start <= end;
    }

}
