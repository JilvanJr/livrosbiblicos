package com.livrosbiblicos.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TagEnum {

    ABREVIACAO("abreviacao"),
    NOME("nome"),
    AUTOR("autor"),
    GRUPO("grupo"),
    TESTAMENTO("testamento");

    private final String tag;
}