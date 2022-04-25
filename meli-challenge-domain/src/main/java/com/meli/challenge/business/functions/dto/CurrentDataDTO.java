package com.meli.challenge.business.functions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentDataDTO {

    private String line;
    private int currentRow;
    private int currentColumn;
}
