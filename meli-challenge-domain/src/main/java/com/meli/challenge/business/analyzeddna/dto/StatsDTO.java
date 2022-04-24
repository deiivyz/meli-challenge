package com.meli.challenge.business.analyzeddna.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO {
    private int countMutantDna;
    private int countHumanDna;
    private String ratio;
}
