package com.meli.challenge.functions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzerFunctionTest {

    @InjectMocks
    AnalyzerFunction analyzer;

    @Test
    public void test() {
        // prepare

        String[] dna = {"AAAA", "TTTT", "ACGT", "GGCT"};

        // execute

        boolean result = analyzer.test(dna);

        // assers

        assertTrue(result);
    }
}
