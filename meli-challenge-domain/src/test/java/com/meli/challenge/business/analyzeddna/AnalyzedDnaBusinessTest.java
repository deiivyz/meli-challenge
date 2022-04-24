package com.meli.challenge.business.analyzeddna;

import com.meli.challenge.business.analyzeddna.dto.StatsDTO;
import com.meli.challenge.business.analyzeddna.dto.TotalsDTO;
import com.meli.challenge.entity.AnalyzedDna;
import com.meli.challenge.repository.AnalyzedDnaRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzedDnaBusinessTest {

    @Mock
    AnalyzedDnaRepository repository;

    @InjectMocks
    AnalyzedDnaBusiness business;

    @Test
    public void getStats() {
        // prepare

        TotalsDTO totals = TotalsDTO
            .builder()
            .humans(10)
            .mutants(5)
            .build();

        when(repository.getStats()).thenReturn(totals);

        // execute

        StatsDTO result = business.getStats();

        // asserts

        assertNotNull(result);
        assertEquals("0,5", result.getRatio());
    }

    @Test
    public void findByDna() {
        // prepare

        String[] dna = {"AAAA", "TTTT", "ACGT", "GGCT"};

        AnalyzedDna analyzedDna = AnalyzedDna
            .builder()
            .dna(dna)
            .isMutant(true)
            .build();

        when(repository.findByDna(eq(dna))).thenReturn(Optional.of(analyzedDna));

        // execute

        Optional<AnalyzedDna> result = business.findByDna(dna);

        // asserts

        assertNotNull(result);
        assertTrue(result.isPresent());
    }

    @Test
    public void saveDna() {
        // prepare

        String[] dna = {"AACG", "TTTT", "GCGT", "AACT"};

        // execute

        business.saveDna(dna, false);

        // asserts

        verify(repository).save(any(AnalyzedDna.class));

    }
}
