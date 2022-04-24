package com.meli.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(
    collection = "analyzed_dna")
public class AnalyzedDna {

    @Id
    private String id;
    private String[] dna;
    private boolean isMutant;
}
