package com.meli.challenge.business.builder;

import com.meli.challenge.business.builder.dto.BuilderDTO;
import com.meli.challenge.business.functions.dto.CurrentDataDTO;
import com.meli.challenge.utils.Constants;
import com.meli.challenge.utils.AnalyzeUtil;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class BuilderDiagonalRightUp implements IBuilderWord {

    private BuilderDTO dto;

    public BuilderDiagonalRightUp(int aux) {
        this.dto = BuilderDTO
            .builder()
            .word(Constants.EMPTY)
            .aux(aux)
            .build();
    }

    /**
     * Método encargado de construir las palabras de manera oblicua de la parte
     * superior derecha (Se incluye la diagonal inversa)
     * @param CurrentDataDTO
     */
    public void build(CurrentDataDTO currentData) {
        int position = currentData.getCurrentRow() + currentData.getCurrentColumn();

        if (position < currentData.getLine().length()) {
            this.dto.setWord(AnalyzeUtil.concat(this.dto.getWord(),
                AnalyzeUtil.getLetter(currentData.getLine(), position)));
        }
    }

    public String getWord() {
        return this.dto.getWord();
    }
}
