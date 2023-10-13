package com.livrosbiblicos.funcional;

import com.livrosbiblicos.BaseTestFuncional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static com.livrosbiblicos.common.constante.TesteConstantes.*;
import static com.livrosbiblicos.common.enums.ParametroEnum.*;
import static com.livrosbiblicos.common.enums.TagEnum.*;
import static com.livrosbiblicos.common.enums.UrlEnum.LIVROS;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Sql(scripts = "classpath:sql/Criacao_Tabela_Livros.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = "classpath:sql/InserirLivros.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/Criacao_Tabela_Livros.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/InserirLivros.sql", executionPhase = BEFORE_TEST_METHOD)
public class ConsultarLivrosTest extends BaseTestFuncional {

    @Test
    @DisplayName("Consulta todos os livros")
    public void deveRetornarTodosLivros() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
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
    public void deveRetornarLivrosPorAutor() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON)
                        .queryParam(PARAM_AUTOR.getParametro(), AUTOR_MOISES))
                .andExpect(status().isOk())
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
    public void deveRetornarLivrosPorGrupo() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON)
                        .queryParam(PARAM_GRUPO.getParametro(), GRUPO_EVANGELHOS))
                .andExpect(status().isOk())
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
    public void deveRetornarLivrosPorTestamento() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON)
                        .queryParam(PARAM_TESTAMENTO.getParametro(), TESTAMENTO_NT))
                .andExpect(status().isOk())
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
    public void deveRetornarLivrosPorTodosParametros() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON)
                        .queryParam(PARAM_AUTOR.getParametro(), AUTOR_PAULO)
                        .queryParam(PARAM_GRUPO.getParametro(), GRUPO_CARTAS_PAULO)
                        .queryParam(PARAM_TESTAMENTO.getParametro(), TESTAMENTO_NT))
                .andExpect(status().isOk())
                .andExpect(jsonPath(BODY, hasSize(1)))
                .andExpect(tagBodyByPosicao(0, ABREVIACAO.getTag(), ABREVIACAO_RM))
                .andExpect(tagBodyByPosicao(0, NOME.getTag(), NOME_RM))
                .andExpect(tagBodyByPosicao(0, AUTOR.getTag(), AUTOR_PAULO))
                .andExpect(tagBodyByPosicao(0, GRUPO.getTag(), GRUPO_CARTAS_PAULO))
                .andExpect(tagBodyByPosicao(0, TESTAMENTO.getTag(), TESTAMENTO_NT))
                .andDo(print());
    }

    @Test
    @DisplayName("Não consulta passando parametros inexistentes")
    public void naoDeveRetornarLivros() throws Exception {

        getMvc().perform(get(LIVROS.getUrl())
                        .contentType(APPLICATION_JSON)
                        .queryParam(PARAM_TESTAMENTO.getParametro(), "NAO EXISTE"))
//                        .queryParam(PARAM_GRUPO.getParametro(), GRUPO_CARTAS_PAULO)
//                        .queryParam(PARAM_TESTAMENTO.getParametro(), TESTAMENTO_NT))
                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath(BODY, hasSize(0)))
                .andDo(print());
    }
}