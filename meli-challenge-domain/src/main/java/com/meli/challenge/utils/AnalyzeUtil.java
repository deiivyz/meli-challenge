package com.meli.challenge.utils;

public class AnalyzeUtil {

    private AnalyzeUtil() {
        // Do nothing
    }

    public static char getLetter(String word, int position) {
        return word.charAt(position);
    }

    public static String concat(String currentWord, char newWord) {
        return String.format("%s%s", currentWord, newWord);
    }

    public static int validateSequence(String value) {
        return value.length() >= Constants.MINUMIM_LETTERS &&
            Constants.ALLOWED_SEQUENCE.stream().anyMatch(value::contains)
                ? 1
                : 0;
    }
}
