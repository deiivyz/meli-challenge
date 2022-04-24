package com.meli.challenge.business.mutant;

import com.meli.challenge.business.analyzeddna.AnalyzedDnaBusiness;
import com.meli.challenge.business.analyzeddna.dto.StatsDTO;
import com.meli.challenge.business.mutant.dto.DnaRequestDTO;
import com.meli.challenge.configuration.exceptions.NoMutantException;
import com.meli.challenge.entity.AnalyzedDna;
import java.util.Optional;
import java.util.function.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MutantBusinessTest {

    @Mock
    Predicate<String[]> analyzer;

    @Mock
    AnalyzedDnaBusiness analyzedDnaBusiness;

    @InjectMocks
    MutantBusiness business;

    @Test
    public void validateDnaMutan() {
        // prepare

        DnaRequestDTO requestDto = DnaRequestDTO
            .builder()
            .dna(new String[] {"AAAA", "TTTT", "ACGT", "GGCT"})
            .build();

        when(analyzedDnaBusiness.findByDna(eq(requestDto.getDna()))).thenReturn(Optional.ofNullable(null));

        when(analyzer.test(eq(requestDto.getDna()))).thenReturn(true);

        // execute

        business.validateDna(requestDto);

        // asserts

        verify(analyzer, times(1)).test(eq(requestDto.getDna()));
        verify(analyzedDnaBusiness, times(1)).saveDna(eq(requestDto.getDna()), eq(true));
    }

    @Test(
        expected = NoMutantException.class)
    public void validateDnaNotMutant() {
        // prepare

        DnaRequestDTO requestDto = DnaRequestDTO
            .builder()
            .dna(new String[] {"AACG", "TTTT", "GCGT", "AACT"})
            .build();

        when(analyzedDnaBusiness.findByDna(eq(requestDto.getDna()))).thenReturn(Optional.ofNullable(null));

        when(analyzer.test(eq(requestDto.getDna()))).thenReturn(false);

        // execute

        business.validateDna(requestDto);

    }

    @Test
    public void validateDnaExist() {
        // prepare

        DnaRequestDTO requestDto = DnaRequestDTO
            .builder()
            .dna(new String[] {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"})
            .build();

        AnalyzedDna analyzeDna = AnalyzedDna
            .builder()
            .dna(requestDto.getDna())
            .isMutant(true)
            .build();

        when(analyzedDnaBusiness.findByDna(eq(requestDto.getDna()))).thenReturn(Optional.of(analyzeDna));

        // execute

        business.validateDna(requestDto);

        // asserts

        verify(analyzedDnaBusiness, times(0)).saveDna(eq(requestDto.getDna()), eq(true));
    }

    @Test
    public void getStats() {
        // prepare

        StatsDTO stats = StatsDTO
            .builder()
            .countHumanDna(10)
            .countMutantDna(5)
            .ratio("0,5")
            .build();

        when(analyzedDnaBusiness.getStats()).thenReturn(stats);

        // execute

        StatsDTO result = business.getStats();

        // asserts

        assertEquals(5, result.getCountMutantDna());
    }
}
