package com.meli.challenge.business.builder;

import com.meli.challenge.functions.dto.CurrentDataDTO;

public interface IBuilderWord {

    void build(CurrentDataDTO currentData);

    String getWord();
}
