package com.meli.challenge.configuration.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private int status;
    private String error;
    private String message;
}
