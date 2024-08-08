package com.philectron.algorithms.leetcode.wordsortvowelconsonant;

import java.util.Arrays;
import java.util.List;

public class WordSortVowelConsonant {

    private static List<Character> VOWELS = Arrays.asList('a', 'o', 'e', 'u', 'i');

    public String sort(final String string) {
        final List<String> words = Arrays.asList(string.split(" "));
        words.sort(this::compareByVcDiffThenAlphabetical);
        return String.join(" ", words);
    }

    private int compareByVcDiffThenAlphabetical(final String lhs, final String rhs) {
        final int vcDiffLhs = vowelConsonantDiff(lhs);
        final int vcDiffRhs = vowelConsonantDiff(rhs);
        if (vcDiffLhs == vcDiffRhs) {
            return lhs.compareTo(rhs);
        }
        return Integer.compare(vcDiffLhs, vcDiffRhs);
    }

    private int vowelConsonantDiff(final String word) {
        return Math.abs(vowelCount(word) - consonantCount(word));
    }

    private int vowelCount(final String word) {
        return word.toLowerCase()
                .chars()
                .mapToObj(ch -> (char) ch)
                .filter(ch -> VOWELS.contains(ch))
                .toList()
                .size();
    }

    private int consonantCount(final String word) {
        return word.length() - vowelCount(word);
    }

}
