package com.philectron.algorithms.logic;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssertionTest {

    @Test
    public void assertNotNull_onNullReference_failsWithAssertionError() {
        assertThrows(AssertionError.class, () -> Assertion.assertNotNull(null));
    }

    @Test
    public void assertNotNull_onNonNullReference_returnsReference() {
        Integer number = 1;
        assertThat(Assertion.assertNotNull(number)).isEqualTo(number);
    }

    @Test
    public void assertElementIndex_onOutOfBoundIndex_failsWithAssertionError() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndex(0, 0));
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndex(-1, 0));
    }

    @Test
    public void assertElementIndex_onNegativeSize_failsWithAssertionError() {
        assertThrows(AssertionError.class, () -> Assertion.assertElementIndex(0, -1));
    }

    @Test
    public void assertElementIndex_onInBoundIndex_returnsIndex() {
        final int index = 1;
        assertThat(Assertion.assertElementIndex(index, index + 1)).isEqualTo(index);
    }

}
