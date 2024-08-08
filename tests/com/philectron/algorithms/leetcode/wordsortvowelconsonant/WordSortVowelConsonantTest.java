package com.philectron.algorithms.leetcode.wordsortvowelconsonant;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;

import org.junit.jupiter.api.Test;

public class WordSortVowelConsonantTest {

    private static final WordSortVowelConsonant solution = new WordSortVowelConsonant();

    @Test
    public void sortWords_emptyString() {
        assertThat(solution.sort(""), is(emptyString()));
    }

    @Test
    public void sortWords_oneCharacterString() {
        assertThat(solution.sort("a"), is("a"));
    }

    @Test
    public void sortWords_oneWordString() {
        assertThat(solution.sort("oneWord"), is("oneWord"));
    }

    @Test
    public void sortWords_numberString() {
        assertThat(solution.sort("123 789 456"), is("123 456 789"));
    }

    @Test
    public void sortWords_nonAlphanumericString() {
        assertThat(solution.sort("=== ---"), is("--- ==="));
    }

    @Test
    public void sortWords_vowelOnly() {
        assertThat(solution.sort("aaaaaa ee"), is("ee aaaaaa"));
    }

    @Test
    public void sortWords_consonantOnly() {
        assertThat(solution.sort("cnsnnt nly wrds"), is("nly wrds cnsnnt"));
    }

    @Test
    public void sortWords_exampleCase() {
        assertThat(solution.sort("The quick brown fox jumps over the lazy dog123"),
                is("over The fox quick the lazy brown jumps dog123"));
    }

}
