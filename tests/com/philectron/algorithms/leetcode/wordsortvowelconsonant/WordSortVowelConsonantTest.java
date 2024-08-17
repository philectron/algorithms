package com.philectron.algorithms.leetcode.wordsortvowelconsonant;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class WordSortVowelConsonantTest {

    private static final WordSortVowelConsonant solution = new WordSortVowelConsonant();

    @Test
    public void sortWords_emptyString() {
        assertThat(solution.sort("")).isEmpty();
    }

    @Test
    public void sortWords_oneCharacterString() {
        assertThat(solution.sort("a")).isEqualTo("a");
    }

    @Test
    public void sortWords_oneWordString() {
        assertThat(solution.sort("oneWord")).isEqualTo("oneWord");
    }

    @Test
    public void sortWords_numberString() {
        assertThat(solution.sort("123 789 456")).isEqualTo("123 456 789");
    }

    @Test
    public void sortWords_nonAlphanumericString() {
        assertThat(solution.sort("=== ---")).isEqualTo("--- ===");
    }

    @Test
    public void sortWords_vowelOnly() {
        assertThat(solution.sort("aaaaaa ee")).isEqualTo("ee aaaaaa");
    }

    @Test
    public void sortWords_consonantOnly() {
        assertThat(solution.sort("cnsnnt nly wrds")).isEqualTo("nly wrds cnsnnt");
    }

    @Test
    public void sortWords_exampleCase() {
        assertThat(solution.sort("The quick brown fox jumps over the lazy dog123"))
                .isEqualTo("over The fox quick the lazy brown jumps dog123");
    }

}
