package com.meli.challenge.business.builder;

import com.meli.challenge.business.builder.dto.BuilderDTO;
import com.meli.challenge.business.functions.dto.CurrentDataDTO;
import com.meli.challenge.utils.AnalyzeUtil;
import com.meli.challenge.utils.Constants;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class BuilderDiagonalLeftUp implements IBuilderWord {

    private BuilderDTO dto;

    public BuilderDiagonalLeftUp(int aux) {
        this.dto = BuilderDTO
            .builder()
            .word(Constants.EMPTY)
            .aux(aux)
            .build();
    }

    /**
     * Método encargado de construir las palabras de manera oblicua de la parte
     * superior izquierda (Se incluye la diagonal inversa)
     * @param CurrentDataDTO
     */
    public void build(CurrentDataDTO currentData) {
        int position = this.dto.getAux() - currentData.getCurrentRow();

        if (position >= 0) {
            this.dto.setWord(AnalyzeUtil.concat(this.dto.getWord(),
                AnalyzeUtil.getLetter(currentData.getLine(), position)));
            this.dto.previous();
        }
    }

    public String getWord() {
        return this.dto.getWord();
    }
}
