package com.meli.challenge.business.analyzeddna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO {
    @JsonProperty("count_human_dna")
    private int countHumanDna;
    @JsonProperty("count_mutant_dna")
    private int countMutantDna;
    @JsonProperty("ratio")
    private String ratio;
}
