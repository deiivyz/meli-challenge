package com.meli.challenge.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {

    public static final String EMPTY = "";
    public static final List<String> ALLOWED_SEQUENCE = Collections.unmodifiableList(
        Arrays.asList("AAAA", "CCCC", "GGGG", "TTTT"));
    public static final int MINUMIM_MATCHES_REQUIRED = 2;
    public static final int MINUMIM_LETTERS = 4;

    private Constants() {
        // Do nothing
    }
}
