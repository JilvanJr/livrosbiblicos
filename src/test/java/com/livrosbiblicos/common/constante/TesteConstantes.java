package com.livrosbiblicos.common.constante;

import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class TesteConstantes {

    public static final String BODY = "$";
    public static final String ABREVIACAO_GN = "Gn";
    public static final String ABREVIACAO_EX = "Êx";
    public static final String ABREVIACAO_LV = "Lv";
    public static final String ABREVIACAO_MT = "Mt";
    public static final String ABREVIACAO_MC = "Mc";
    public static final String ABREVIACAO_RM = "Rm";
    public static final String NOME_GN = "Gênesis";
    public static final String NOME_EX = "Êxodo";
    public static final String NOME_LV = "Levítico";
    public static final String NOME_MT = "Mateus";
    public static final String NOME_MC = "Marcos";
    public static final String NOME_RM = "Romanos";
    public static final String AUTOR_MOISES = "Móises";
    public static final String AUTOR_MATEUS = "Mateus";
    public static final String AUTOR_MARCOS = "Marcos";
    public static final String AUTOR_PAULO = "Paulo";
    public static final String GRUPO_PENTATEUCO = "Pentateuco";
    public static final String GRUPO_EVANGELHOS = "Evangelhos";
    public static final String GRUPO_CARTAS_PAULO = "Cartas de Paulo";
    public static final String TESTAMENTO_VT = "VT";
    public static final String TESTAMENTO_NT = "NT";
    public static final String ERROR_NAO_ENCONTRADO = "Não encontrado";
    public static final String MSG_AUTOR_INVALIDO = "Autor inválido";
    public static final String MSG_GRUPO_INVALIDO = "Grupo inválido";
    public static final String MSG_LIVRO_NAO_ENCONTRADO = "Livro não encontrado";
    public static final String MSG_TESTAMENTO_INVALIDO = "Testamento inválido";



    public static <T> ResultMatcher tagBodyByPosicao(Integer posicao, String nomeTag, T valorTag) {
        return jsonPath(String.format("[%d].%s", posicao, nomeTag), Matchers.is(valorTag));
    }

    public static String mensagemParametroInvalido(String parametro, String qtdCaracter) {
        return String.format("O parâmetro '%s' é inválido. Deve conter pelo menos %s caracteres.", parametro, qtdCaracter);
    }
}