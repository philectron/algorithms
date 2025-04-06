package com.philectron.algorithms.logic;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AssertionTest {

    @Test
    void assertNotNull_nullReference_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertNotNull(null));
    }

    @Test
    void assertNotNull_nonNullReference_returnsReference() {
        Integer number = 1;
        assertThat(Assertion.assertNotNull(number)).isEqualTo(number);
    }

    @Test
    void assertNotNegative_negativeNumber_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertNotNegative(-1));
    }

    @Test
    void assertNotNegative_nonNegativeNumber_returnsNumber() {
        assertThat(Assertion.assertNotNegative(0)).isEqualTo(0);
        assertThat(Assertion.assertNotNegative(1)).isEqualTo(1);
    }

    @Test
    void assertElementIndex_negativeSize_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndex(0, -1));
    }

    @Test
    void assertElementIndex_outOfBoundIndex_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndex(0, 0));
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndex(-1, 0));
    }

    @Test
    void assertElementIndex_validIndex_returnsIndex() {
        final int index = 1;
        assertThat(Assertion.assertElementIndex(index, index + 1)).isEqualTo(index);
    }

    @Test
    void assertElementIndexes_negativeSize_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(0, 0, -1));
    }

    @Test
    void assertElementIndexes_outOfBoundIndexes_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(1, 0, 1));
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(-1, 0, 1));

        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(0, 1, 1));
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(0, -1, 1));
    }

    @Test
    void assertElementIndexes_outOfOrderIndexes_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndexes(1, 0, 2));
    }

    @Test
    void assertElementIndexes_validIndexes_succeeds() {
        Assertion.assertElementIndexes(0, 0, 1);
        Assertion.assertElementIndexes(0, 1, 2);
    }

    @Test
    void assertPositionIndex_negativeSize_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertPositionIndex(0, -1));
    }

    @Test
    void assertPositionIndex_outOfBoundIndex_fails() {
        assertThrows(AssertionError.class, () -> Assertion.assertPositionIndex(1, 0));
        assertThrows(AssertionError.class, () -> Assertion.assertPositionIndex(-1, 0));
    }

    @Test
    void assertPositionIndex_validIndex_returnsIndex() {
        final int index = 1;
        assertThat(Assertion.assertPositionIndex(index, index)).isEqualTo(index);
    }

}
