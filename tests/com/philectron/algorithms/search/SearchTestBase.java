package com.philectron.algorithms.search;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.primitives.Ints;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class SearchTestBase {

    private static final int UNIQUE_TARGET = 2;
    private static final int DUPLICATE_TARGET = 5;
    private static final int NON_TARGET = 6;
    private static final List<Integer> LIST =
            List.of(DUPLICATE_TARGET, UNIQUE_TARGET, 8, 7, 4, 3, 9, 7, 1, DUPLICATE_TARGET);
    private static final List<Integer> SORTED_LIST = LIST.stream().sorted().toList();

    private final SearchAlgorithm search;
    private final boolean isSortRequired;

    private List<Integer> list;
    private int target;

    SearchTestBase(SearchAlgorithm search, boolean isSortRequired) {
        this.search = search;
        this.isSortRequired = isSortRequired;
    }

    @BeforeEach
    void setUp() {
        list = isSortRequired ? SORTED_LIST : LIST;
    }

    private void searchAndAssert() {
        int[] array = list.stream().mapToInt(Integer::intValue).toArray();
        int[] originalArray = array.clone();

        assertWithMessage("First index of target %s in array %s", target, Arrays.toString(array))
                .that(search.findFirst(array, target))
                .isEqualTo(Ints.indexOf(array, target));

        assertWithMessage("Last index of target %s in array %s", target, Arrays.toString(array))
                .that(search.findLast(array, target))
                .isEqualTo(Ints.lastIndexOf(array, target));

        assertWithMessage("Whether array %s contains target %s", Arrays.toString(array), target)
                .that(search.contains(array, target))
                .isEqualTo(Ints.contains(array, target));

        assertThat(array).isEqualTo(originalArray); // searching should not mutate the array
    }

    @Test
    void search_nullArray_fails() {
        assertThrows(NullPointerException.class, () -> search.findFirst(null, 0));
        assertThrows(NullPointerException.class, () -> search.findLast(null, 0));
        assertThrows(NullPointerException.class, () -> search.contains(null, 0));
    }

    @Test
    void search_emptyArray_returnsNotFound() {
        list = Collections.emptyList();
        target = 1;
        searchAndAssert();
    }

    @Test
    void search_singletonArray_returnsSingleIndex() {
        list = Collections.singletonList(1);
        target = list.getFirst();
        searchAndAssert();
    }

    @Test
    void search_nCopiesArray() {
        target = 1;
        list = Collections.nCopies(LIST.size(), target);
        searchAndAssert();
    }

    @Test
    void search_uniqueTarget_returnsSingleIndex() {
        target = UNIQUE_TARGET;
        searchAndAssert();
    }

    @Test
    void search_duplicateTargets() {
        target = DUPLICATE_TARGET;
        searchAndAssert();
    }

    @Test
    void search_targetIsMinValue() {
        target = Collections.min(list);
        searchAndAssert();
    }

    @Test
    void search_targetIsMaxValue() {
        target = Collections.max(list);
        searchAndAssert();
    }

    @Test
    void search_targetAtStartOfList() {
        target = list.getFirst();
        searchAndAssert();
    }

    @Test
    void search_targetAtMiddleOfList() {
        target = list.get(list.size() / 2);
        searchAndAssert();
    }

    @Test
    void search_targetAtEndOfList() {
        target = list.getFirst();
        searchAndAssert();
    }

    @Test
    void search_nonTarget_returnsNotFound() {
        target = NON_TARGET;
        searchAndAssert();
    }

    @Test
    void search_targetLessThanMin_returnsNotFound() {
        target = Collections.min(list) - 1;
        searchAndAssert();
    }

    @Test
    void search_targetGreaterThanMax_returnsNotFound() {
        target = Collections.max(list) + 1;
        searchAndAssert();
    }

}
