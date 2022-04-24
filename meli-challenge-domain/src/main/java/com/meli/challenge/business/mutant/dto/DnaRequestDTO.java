package com.meli.challenge.business.mutant.dto;

import com.meli.challenge.annotations.DnaValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaRequestDTO {

    @DnaValidation
    private String[] dna;
}
