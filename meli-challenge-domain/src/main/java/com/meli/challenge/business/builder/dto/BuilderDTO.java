package com.meli.challenge.business.builder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuilderDTO {

    private String word;
    private int aux;

    public void next() {
        this.aux++;
    }

    public void previous() {
        this.aux--;
    }
}
