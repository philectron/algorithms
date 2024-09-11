package com.philectron.algorithms.datastructures.sets;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.philectron.algorithms.datastructures.interfaces.Indexable;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class IndexableTestBase {

    private static final List<Integer> VALUES = List.of(100, 400, 700, 200, 500, 300, 600, 100);
    private static final List<Integer> VALUES_UNIQUE_SORTED =
            VALUES.stream().distinct().sorted().toList();

    private Indexable<Integer> indexable;
    private Indexable<Integer> emptyIndexable;

    abstract Indexable<Integer> createIndexable(Iterable<Integer> iterable);

    @BeforeEach
    private void setUp() {
        indexable = createIndexable(VALUES);
        emptyIndexable = createIndexable(Collections.emptyList());
        assertThat(indexable).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
        assertThat(emptyIndexable).isEmpty();
    }

    @Test
    public void get_emptyIndexable_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyIndexable.get(0));
    }

    @Test
    public void get_indexOutOfBound_fails() {
        assertThrows(IndexOutOfBoundsException.class, () -> indexable.get(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> indexable.get(VALUES_UNIQUE_SORTED.size()));
    }

    @Test
    public void get_returnsValue() {
        for (int i = 0; i < VALUES_UNIQUE_SORTED.size(); i++) {
            assertThat(indexable.get(i)).isEqualTo(VALUES_UNIQUE_SORTED.get(i));
        }
    }

    @Test
    public void indexOf_nullValue_fails() {
        assertThrows(NullPointerException.class, () -> emptyIndexable.indexOf(null));
        assertThrows(NullPointerException.class, () -> indexable.indexOf(null));
    }

    @Test
    public void indexOf_emptyIndexable_returnsNotFound() {
        assertThat(emptyIndexable.indexOf(1)).isEqualTo(-1);
        // Searching should not mutate the list.
        assertThat(emptyIndexable).isEmpty();
    }

    @Test
    public void indexOf_valueInIndexable_returnsIndex() {
        // Test the initial searches.
        for (int i = 0; i < VALUES_UNIQUE_SORTED.size(); i++) {
            assertThat(indexable.indexOf(VALUES_UNIQUE_SORTED.get(i))).isEqualTo(i);
        }

        // Searching should not mutate the indexable.
        assertThat(indexable).containsExactlyElementsIn(VALUES_UNIQUE_SORTED).inOrder();
    }

}
