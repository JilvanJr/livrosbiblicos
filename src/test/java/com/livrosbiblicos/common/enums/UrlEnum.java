package com.livrosbiblicos.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UrlEnum {

    LIVROS("/livros"),
    LIVROS_ID("/livros/{id}"),
    LIVROS_ID_DETALHES("/livros/{id}/detalhes");

    private final String url;
}