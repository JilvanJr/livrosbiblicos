package com.livrosbiblicos.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UrlEnum {

    LIVROS("/livros");

    private final String url;
}