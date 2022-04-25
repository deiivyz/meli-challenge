package com.meli.challenge.business.analyzeddna.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO {
    @JsonAlias("count_mutant_dna")
    private int countMutantDna;
    @JsonAlias("count_human_dna")
    private int countHumanDna;
    private String ratio;
}
