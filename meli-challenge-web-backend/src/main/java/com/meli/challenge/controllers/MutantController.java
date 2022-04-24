package com.meli.challenge.controllers;

import com.meli.challenge.business.analyzeddna.dto.StatsDTO;
import com.meli.challenge.business.mutant.MutantBusiness;
import com.meli.challenge.business.mutant.dto.DnaRequestDTO;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {

    @Autowired
    MutantBusiness business;

    @ApiOperation(
        value = "Validar adn mutantes",
        notes = "Si la validación del adn recibido indica que es un mutante el servicio retornará un HTTP 200-OK, en caso contrario un 403-Forbidden")
    @PostMapping("/mutant/")
    public void validateDna(@Valid @RequestBody DnaRequestDTO requestDto) {
        business.validateDna(requestDto);
    }

    @ApiOperation(
        value = "Verificaciones de ADN",
        notes = "Consultar estadísticas de verificaciones de ADN")
    @GetMapping("/stats/")
    public StatsDTO stats() {
        return business.getStats();
    }
}
