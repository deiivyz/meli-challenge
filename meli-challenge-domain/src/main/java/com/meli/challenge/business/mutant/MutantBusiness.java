package com.meli.challenge.business.mutant;

import com.meli.challenge.business.analyzeddna.AnalyzedDnaBusiness;
import com.meli.challenge.business.analyzeddna.dto.StatsDTO;
import com.meli.challenge.business.mutant.dto.DnaRequestDTO;
import com.meli.challenge.configuration.exceptions.NoMutantException;
import com.meli.challenge.entity.AnalyzedDna;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutantBusiness {

    private Predicate<String[]> analyzer;
    private AnalyzedDnaBusiness analyzedDnaBusiness;

    @Autowired
    public MutantBusiness(Predicate<String[]> analyzer,
        AnalyzedDnaBusiness analyzedDnaBusiness) {
        Objects.requireNonNull(analyzer);
        Objects.requireNonNull(analyzedDnaBusiness);

        this.analyzer = analyzer;
        this.analyzedDnaBusiness = analyzedDnaBusiness;
    }

    
    /**
     * Método encargado de orquestar la verificación del adn y almacenar sus datos.
     * @param DnaRequestDTO
     */
    public void validateDna(DnaRequestDTO requestDto) {

        Optional<AnalyzedDna> existDna = this.analyzedDnaBusiness.findByDna(requestDto.getDna());

        boolean isMutant = false;

        if (!existDna.isPresent()) {
            isMutant = this.analyzer.test(requestDto.getDna());
            this.analyzedDnaBusiness.saveDna(requestDto.getDna(), isMutant);
        } else {
            isMutant = existDna.get().isMutant();
        }

        Optional
            .of(isMutant)
            .filter(r -> r)
            .orElseThrow(NoMutantException::new);
    }

    /**
     * Método encargado de solicitar la información de las estadísticas de los datos procesados.
     * @return StatsDTO
     */
    public StatsDTO getStats() {
        return this.analyzedDnaBusiness.getStats();
    }
}
