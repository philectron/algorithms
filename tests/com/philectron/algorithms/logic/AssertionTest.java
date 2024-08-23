package com.philectron.algorithms.logic;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssertionTest {

    @Test
    public void assertNotNull_nullReference_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertNotNull(null));
    }

    @Test
    public void assertNotNull_nonNullReference_returnsReference() {
        Integer number = 1;
        assertThat(Assertion.assertNotNull(number)).isEqualTo(number);
    }

    @Test
    public void assertElementIndex_negativeSize_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndex(0, -1));

    }

    @Test
    public void assertElementIndex_outOfBoundIndex_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndex(0, 0));
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndex(-1, 0));
    }

    @Test
    public void assertElementIndex_validIndex_returnsIndex() {
        final int index = 1;
        assertThat(Assertion.assertElementIndex(index, index + 1)).isEqualTo(index);
    }

    @Test
    public void assertElementIndexes_negativeSize_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(0, 0, -1));
    }

    @Test
    public void assertElementIndexes_outOfBoundIndexes_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(1, 0, 1));
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(-1, 0, 1));

        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(0, 1, 1));
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(0, -1, 1));
    }

    @Test
    public void assertElementIndexes_outOfOrderIndexes_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(1, 0, 2));
    }

    @Test
    public void assertElementIndexes_validIndexes_succeeds() {
        Assertion.assertElementIndexes(0, 0, 1);
        Assertion.assertElementIndexes(0, 1, 2);
    }

}
