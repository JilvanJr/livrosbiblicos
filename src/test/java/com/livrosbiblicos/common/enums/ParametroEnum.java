package com.livrosbiblicos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParametroEnum {
    PARAM_AUTOR("autor"),
    PARAM_GRUPO("grupo"),
    PARAM_TESTAMENTO("testamento"),
    PATH_PARAM("{id}"),
    TAG_STATUS_CODE("status");

    private final String parametro;
}