package com.philectron.algorithms.searching;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import com.google.common.primitives.Ints;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public abstract class SearchTestBase {

    private static final int UNIQUE_TARGET = 2;
    private static final int DUPLICATE_TARGET = 5;
    private static final int NON_TARGET = 10;
    private static final List<Integer> LIST =
            List.of(DUPLICATE_TARGET, UNIQUE_TARGET, 8, 6, 4, 3, 5, 7, 1, DUPLICATE_TARGET);
    private static final List<Integer> SORTED_LIST = LIST.stream().sorted().toList();

    private final SearchingAlgorithm searcher;
    private final boolean isSortRequired;

    private List<Integer> list;
    private int target;

    SearchTestBase(SearchingAlgorithm searcher, boolean isSortRequired) {
        this.searcher = searcher;
        this.isSortRequired = isSortRequired;
    }

    @AfterEach
    public void searchAndAssert() {
        int[] array = list.stream().mapToInt(Integer::intValue).toArray();
        int[] originalArray = array.clone();

        assertWithMessage("First index of target %s in array %s", target, Arrays.toString(array))
                .that(searcher.findFirst(array, target))
                .isEqualTo(Ints.indexOf(array, target));

        assertWithMessage("Last index of target %s in array %s", target, Arrays.toString(array))
                .that(searcher.findLast(array, target))
                .isEqualTo(Ints.lastIndexOf(array, target));

        assertWithMessage("Whether array %s contains target %s", Arrays.toString(array), target)
                .that(searcher.contains(array, target))
                .isEqualTo(Ints.contains(array, target));

        assertThat(array).isEqualTo(originalArray); // searching should not mutate the array
    }

    @Test
    public void search_emptyArray_returnsNotFound() {
        list = Collections.emptyList();
        target = 1;
    }

    @Test
    public void search_singletonArray_returnsSingleIndex() {
        list = Collections.singletonList(1);
        target = list.get(0);
    }

    @Test
    public void search_nCopiesArray() {
        list = Collections.nCopies(3, target);
        target = 1;
    }

    @Test
    public void search_uniqueTarget_returnsSingleIndex() {
        list = isSortRequired ? SORTED_LIST : LIST;
        target = UNIQUE_TARGET;
    }

    @Test
    public void search_duplicateTargets() {
        list = isSortRequired ? SORTED_LIST : LIST;
        target = DUPLICATE_TARGET;
    }

    @Test
    public void search_nonTarget_returnsNotFound() {
        list = isSortRequired ? SORTED_LIST : LIST;
        target = NON_TARGET;
    }

}
