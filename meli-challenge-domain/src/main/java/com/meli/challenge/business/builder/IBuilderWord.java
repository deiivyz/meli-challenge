package com.meli.challenge.business.builder;

import com.meli.challenge.business.functions.dto.CurrentDataDTO;

public interface IBuilderWord {

    void build(CurrentDataDTO currentData);

    String getWord();
}
