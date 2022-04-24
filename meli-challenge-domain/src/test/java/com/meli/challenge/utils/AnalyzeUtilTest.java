package com.meli.challenge.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzeUtilTest {

    @Test
    public void getLetter() {
        // prepare

        String word = "ACGTC";

        // execute

        char result = AnalyzeUtil.getLetter(word, 2);

        // assers

        assertEquals('G', result);
    }

    @Test
    public void concat() {
        // prepare

        String currentWord = "AA";

        // execute

        String result = AnalyzeUtil.concat(currentWord, 'T');

        // assers

        assertEquals("AAT", result);
    }

    @Test
    public void validateSequence() {
        // prepare

        String value = "AGTTTT";

        // execute

        int result = AnalyzeUtil.validateSequence(value);

        // assers

        assertEquals(1, result);
    }

    @Test
    public void validateSequenceNoMatch() {
        // prepare

        String value = "AGTGTT";

        // execute

        int result = AnalyzeUtil.validateSequence(value);

        // assers

        assertEquals(0, result);
    }

    @Test
    public void validateSequenceInvalidLength() {
        // prepare

        String value = "GT";

        // execute

        int result = AnalyzeUtil.validateSequence(value);

        // assers

        assertEquals(0, result);
    }
}
