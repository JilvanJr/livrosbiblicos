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
    TESTAMENTO("testamento"),

    TAG_ERROR("error"),
    TAG_ID("id"),
    TAG_LIVRO_ID_IS_ID("livro_id.id"),
    TAG_LIVRO_ID_IS_NOME("livro_id.nome"),
    TAG_MAIS_20_CAP("mais20_cap"),
    TAG_MESSAGE("message"),
    TAG_PATH("path"),
    TAG_QTD_CAPITULOS("qtd_capitulos"),
    TAG_QTD_VERSICULOS("qtd_versiculos"),
    TAG_STATUS("status");

    private final String tag;
}