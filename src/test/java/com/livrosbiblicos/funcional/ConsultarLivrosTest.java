package com.livrosbiblicos.funcional;

import com.livrosbiblicos.BaseTestFuncional;
import com.livrosbiblicos.common.enums.ParametroEnum;
import com.livrosbiblicos.provider.LivrosBadRequestProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.test.web.servlet.MvcResult;

import static com.livrosbiblicos.common.constante.MatcherConstantes.*;
import static com.livrosbiblicos.common.constante.TesteConstantes.*;
import static com.livrosbiblicos.common.enums.ParametroEnum.*;
import static com.livrosbiblicos.common.enums.TagEnum.*;
import static com.livrosbiblicos.common.enums.UrlEnum.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static wiremock.com.jayway.jsonpath.JsonPath.read;
import static wiremock.org.apache.commons.lang3.StringUtils.SPACE;

class ConsultarLivrosTest extends BaseTestFuncional {

    @Test
    @DisplayName("Consulta todos os livros")
    void deveRetornarTodosLivros() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON))
                .andExpect(MATCHER_STATUS_IS_OK)
                .andExpect(jsonPath(BODY, hasSize(6)))
                .andExpect(tagBodyByPosicao(1, ABREVIACAO.getTag(), ABREVIACAO_EX))
                .andExpect(tagBodyByPosicao(1, NOME.getTag(), NOME_EX))
                .andExpect(tagBodyByPosicao(1, AUTOR.getTag(), AUTOR_MOISES))
                .andExpect(tagBodyByPosicao(1, GRUPO.getTag(), GRUPO_PENTATEUCO))
                .andExpect(tagBodyByPosicao(1, TESTAMENTO.getTag(), TESTAMENTO_VT))
                .andDo(print());
    }

    @Test
    @DisplayName("Consulta todos os livros do autor Moises")
    void deveRetornarLivrosPorAutor() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON)
                        .queryParam(PARAM_AUTOR.getParametro(), AUTOR_MOISES))
                .andExpect(MATCHER_STATUS_IS_OK)
                .andExpect(jsonPath(BODY, hasSize(3)))
                .andExpect(tagBodyByPosicao(0, ABREVIACAO.getTag(), ABREVIACAO_GN))
                .andExpect(tagBodyByPosicao(0, NOME.getTag(), NOME_GN))
                .andExpect(tagBodyByPosicao(0, AUTOR.getTag(), AUTOR_MOISES))
                .andExpect(tagBodyByPosicao(0, GRUPO.getTag(), GRUPO_PENTATEUCO))
                .andExpect(tagBodyByPosicao(0, TESTAMENTO.getTag(), TESTAMENTO_VT))
                .andExpect(tagBodyByPosicao(1, ABREVIACAO.getTag(), ABREVIACAO_EX))
                .andExpect(tagBodyByPosicao(1, NOME.getTag(), NOME_EX))
                .andExpect(tagBodyByPosicao(1, AUTOR.getTag(), AUTOR_MOISES))
                .andExpect(tagBodyByPosicao(1, GRUPO.getTag(), GRUPO_PENTATEUCO))
                .andExpect(tagBodyByPosicao(1, TESTAMENTO.getTag(), TESTAMENTO_VT))
                .andExpect(tagBodyByPosicao(2, ABREVIACAO.getTag(), ABREVIACAO_LV))
                .andExpect(tagBodyByPosicao(2, NOME.getTag(), NOME_LV))
                .andExpect(tagBodyByPosicao(2, AUTOR.getTag(), AUTOR_MOISES))
                .andExpect(tagBodyByPosicao(2, GRUPO.getTag(), GRUPO_PENTATEUCO))
                .andExpect(tagBodyByPosicao(2, TESTAMENTO.getTag(), TESTAMENTO_VT))
                .andDo(print());
    }

    @Test
    @DisplayName("Consulta todos os livros do grupo dos evangelhos")
    void deveRetornarLivrosPorGrupo() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON)
                        .queryParam(PARAM_GRUPO.getParametro(), GRUPO_EVANGELHOS))
                .andExpect(MATCHER_STATUS_IS_OK)
                .andExpect(jsonPath(BODY, hasSize(2)))
                .andExpect(tagBodyByPosicao(0, ABREVIACAO.getTag(), ABREVIACAO_MT))
                .andExpect(tagBodyByPosicao(0, NOME.getTag(), NOME_MT))
                .andExpect(tagBodyByPosicao(0, AUTOR.getTag(), AUTOR_MATEUS))
                .andExpect(tagBodyByPosicao(0, GRUPO.getTag(), GRUPO_EVANGELHOS))
                .andExpect(tagBodyByPosicao(0, TESTAMENTO.getTag(), TESTAMENTO_NT))
                .andExpect(tagBodyByPosicao(1, ABREVIACAO.getTag(), ABREVIACAO_MC))
                .andExpect(tagBodyByPosicao(1, NOME.getTag(), NOME_MC))
                .andExpect(tagBodyByPosicao(1, AUTOR.getTag(), AUTOR_MARCOS))
                .andExpect(tagBodyByPosicao(1, GRUPO.getTag(), GRUPO_EVANGELHOS))
                .andExpect(tagBodyByPosicao(1, TESTAMENTO.getTag(), TESTAMENTO_NT))
                .andDo(print());
    }

    @Test
    @DisplayName("Consulta todos os livros do novo testamento")
    void deveRetornarLivrosPorTestamento() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON)
                        .queryParam(PARAM_TESTAMENTO.getParametro(), TESTAMENTO_NT))
                .andExpect(MATCHER_STATUS_IS_OK)
                .andExpect(jsonPath(BODY, hasSize(3)))
                .andExpect(tagBodyByPosicao(0, ABREVIACAO.getTag(), ABREVIACAO_MT))
                .andExpect(tagBodyByPosicao(0, NOME.getTag(), NOME_MT))
                .andExpect(tagBodyByPosicao(0, AUTOR.getTag(), AUTOR_MATEUS))
                .andExpect(tagBodyByPosicao(0, GRUPO.getTag(), GRUPO_EVANGELHOS))
                .andExpect(tagBodyByPosicao(0, TESTAMENTO.getTag(), TESTAMENTO_NT))
                .andExpect(tagBodyByPosicao(1, ABREVIACAO.getTag(), ABREVIACAO_MC))
                .andExpect(tagBodyByPosicao(1, NOME.getTag(), NOME_MC))
                .andExpect(tagBodyByPosicao(1, AUTOR.getTag(), AUTOR_MARCOS))
                .andExpect(tagBodyByPosicao(1, GRUPO.getTag(), GRUPO_EVANGELHOS))
                .andExpect(tagBodyByPosicao(1, TESTAMENTO.getTag(), TESTAMENTO_NT))
                .andExpect(tagBodyByPosicao(2, ABREVIACAO.getTag(), ABREVIACAO_RM))
                .andExpect(tagBodyByPosicao(2, NOME.getTag(), NOME_RM))
                .andExpect(tagBodyByPosicao(2, AUTOR.getTag(), AUTOR_PAULO))
                .andExpect(tagBodyByPosicao(2, GRUPO.getTag(), GRUPO_CARTAS_PAULO))
                .andExpect(tagBodyByPosicao(2, TESTAMENTO.getTag(), TESTAMENTO_NT))
                .andDo(print());
    }

    @Test
    @DisplayName("Consulta por todos os parametros")
    void deveRetornarLivrosPorTodosParametros() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON)
                        .queryParam(PARAM_AUTOR.getParametro(), AUTOR_PAULO)
                        .queryParam(PARAM_GRUPO.getParametro(), GRUPO_CARTAS_PAULO)
                        .queryParam(PARAM_TESTAMENTO.getParametro(), TESTAMENTO_NT))
                .andExpect(MATCHER_STATUS_IS_OK)
                .andExpect(jsonPath(BODY, hasSize(1)))
                .andExpect(tagBodyByPosicao(0, ABREVIACAO.getTag(), ABREVIACAO_RM))
                .andExpect(tagBodyByPosicao(0, NOME.getTag(), NOME_RM))
                .andExpect(tagBodyByPosicao(0, AUTOR.getTag(), AUTOR_PAULO))
                .andExpect(tagBodyByPosicao(0, GRUPO.getTag(), GRUPO_CARTAS_PAULO))
                .andExpect(tagBodyByPosicao(0, TESTAMENTO.getTag(), TESTAMENTO_NT))
                .andDo(print());
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @ArgumentsSource(LivrosBadRequestProvider.class)
    void naoDeveRetornarLivros(ParametroEnum parametro, String error, String message, String nomeTeste) throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON)
                        .queryParam(parametro.getParametro(), SPACE))
                .andExpect(MATCHER_STATUS_IS_BAD_REQUEST)
                .andExpect(MATCHER_TAG_STATUS_CODE_IS_BAD_REQUEST)
                .andExpect(jsonPath(TAG_ERROR.getTag(), is(error)))
                .andExpect(jsonPath(TAG_MESSAGE.getTag(), is(message)))
                .andDo(print());
    }

    // livros/{id}

    @Test
    @DisplayName("Consulta livro por id")
    void deveRetornarLivroPorId() throws Exception {

        String idLivro = buscarIdLivro();

        MvcResult result = getMvc().perform(get(LIVROS_ID.getUrl().replace(PATH_PARAM.getParametro(), idLivro))
                        .contentType(APPLICATION_JSON))
                .andExpect(MATCHER_STATUS_IS_OK)
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        String abreviacao = read(jsonResponse, "$.abreviacao");
        assertEquals(ABREVIACAO_GN, abreviacao);
    }

    @Test
    @DisplayName("Não consulta livro com id inválido")
    void naoDeveRetornarLivroComIdInvalido() throws Exception {

        getMvc().perform(get(LIVROS_ID.getUrl().replace(PATH_PARAM.getParametro(), "NAOEXISTE"))
                        .contentType(APPLICATION_JSON))
                .andExpect(MATCHER_STATUS_IS_NOT_FOUND)
                .andExpect(MATCHER_TAG_STATUS_CODE_IS_NOT_FOUND)
                .andExpect(jsonPath(TAG_ERROR.getTag(), is(ERROR_NAO_ENCONTRADO)))
                .andExpect(jsonPath(TAG_MESSAGE.getTag(), is(MSG_LIVRO_NAO_ENCONTRADO)))
                .andExpect(jsonPath(TAG_PATH.getTag(), containsString("NAOEXISTE")))
                .andDo(print());
    }

    @Test
    @DisplayName("Consultar detalhes do livro")
    void deveRetornarDetalhesDoLivro() throws Exception {

        String idLivro = buscarIdLivro();

        getMvc().perform(get(LIVROS_ID_DETALHES.getUrl().replace(PATH_PARAM.getParametro(), idLivro))
                        .contentType(APPLICATION_JSON))
                .andExpect(MATCHER_STATUS_IS_OK)
                .andExpect(tagBodyByPosicao(0, TAG_QTD_CAPITULOS.getTag(), 50))
                .andExpect(tagBodyByPosicao(0, TAG_QTD_VERSICULOS.getTag(), 1533))
                .andExpect(tagBodyByPosicao(0, TAG_MAIS_20_CAP.getTag(), true))
                .andExpect(tagBodyByPosicao(0, TAG_LIVRO_ID_IS_ID.getTag(), idLivro))
                .andExpect(tagBodyByPosicao(0, TAG_LIVRO_ID_IS_NOME.getTag(), NOME_GN))
                .andDo(print());
    }

    @Test
    @DisplayName("Não consulta detalhes do livro com id invalido")
    void naoDeveRetornarDetalhesDoLivroComIdInvalido() throws Exception {

        getMvc().perform(get(LIVROS_ID_DETALHES.getUrl().replace(PATH_PARAM.getParametro(), "NAOEXISTE"))
                        .contentType(APPLICATION_JSON))
                .andExpect(MATCHER_STATUS_IS_NOT_FOUND)
                .andExpect(MATCHER_TAG_STATUS_CODE_IS_NOT_FOUND)
                .andExpect(jsonPath(TAG_ERROR.getTag(), is(ERROR_NAO_ENCONTRADO)))
                .andExpect(jsonPath(TAG_MESSAGE.getTag(), is(MSG_LIVRO_NAO_ENCONTRADO)))
                .andExpect(jsonPath(TAG_PATH.getTag(), containsString("NAOEXISTE")))
                .andDo(print());
    }
}