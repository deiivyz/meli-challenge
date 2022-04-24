package com.meli.challenge.business.builder;

import com.meli.challenge.business.builder.dto.BuilderDTO;
import com.meli.challenge.functions.dto.CurrentDataDTO;
import com.meli.challenge.utils.AnalyzeUtil;
import com.meli.challenge.utils.Constants;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class BuilderVertical implements IBuilderWord {

    private BuilderDTO dto;

    public BuilderVertical(int aux) {
        this.dto = BuilderDTO
            .builder()
            .word(Constants.EMPTY)
            .aux(aux)
            .build();
    }

    public void build(CurrentDataDTO currentData) {
        this.dto.setWord(AnalyzeUtil.concat(this.dto.getWord(),
            AnalyzeUtil.getLetter(currentData.getLine(), currentData.getCurrentRow())));
    }

    public String getWord() {
        return this.dto.getWord();
    }

}
