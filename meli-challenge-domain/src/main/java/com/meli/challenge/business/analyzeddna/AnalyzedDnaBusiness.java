package com.meli.challenge.business.analyzeddna;

import com.meli.challenge.business.analyzeddna.dto.StatsDTO;
import com.meli.challenge.business.analyzeddna.dto.TotalsDTO;
import com.meli.challenge.entity.AnalyzedDna;
import com.meli.challenge.repository.AnalyzedDnaRepository;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class AnalyzedDnaBusiness {

    private AnalyzedDnaRepository repository;

    private static DecimalFormat df = new DecimalFormat("#.##");

    @Autowired
    public AnalyzedDnaBusiness(AnalyzedDnaRepository repository) {
        Objects.requireNonNull(repository);

        this.repository = repository;
    }

    @Cacheable(value = "stats")
    public StatsDTO getStats() {
        TotalsDTO totals = this.repository.getStats();

        return StatsDTO
            .builder()
            .countMutantDna(totals.getMutants())
            .countHumanDna(totals.getHumans())
            .ratio(df.format(Float.valueOf(totals.getMutants()) / Float.valueOf(totals.getHumans())))
            .build();
    }

    @Cacheable(
        value = "findDna", unless = "#result == null")
    public Optional<AnalyzedDna> findByDna(String[] dna) {
        return this.repository.findByDna(dna);
    }

    @CacheEvict(value = "stats", allEntries = true)
    public void saveDna(String[] dna, boolean isMutant) {
        this.repository.save(AnalyzedDna
            .builder()
            .dna(dna)
            .isMutant(isMutant)
            .build());
    }
}
