package com.meli.challenge.business.builder;

import com.meli.challenge.business.builder.dto.BuilderDTO;
import com.meli.challenge.functions.dto.CurrentDataDTO;
import com.meli.challenge.utils.Constants;
import com.meli.challenge.utils.AnalyzeUtil;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class BuilderDiagonalRightDown implements IBuilderWord {

    private BuilderDTO dto;

    public BuilderDiagonalRightDown(int aux) {
        this.dto = BuilderDTO
            .builder()
            .word(Constants.EMPTY)
            .aux(aux)
            .build();
    }

    public void build(CurrentDataDTO currentData) {
        if (currentData.getCurrentColumn() > currentData.getCurrentRow()) {
            this.dto.setWord(AnalyzeUtil.concat(this.dto.getWord(),
                AnalyzeUtil.getLetter(currentData.getLine(), dto.getAux())));
            this.dto.next();
        }
    }

    public String getWord() {
        return this.dto.getWord();
    }
}
