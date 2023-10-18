package com.livrosbiblicos.funcional;

import com.livrosbiblicos.BaseTestFuncional;
import com.livrosbiblicos.repository.LivrosRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.livrosbiblicos.common.constante.MatcherConstantes.*;
import static com.livrosbiblicos.common.constante.TesteConstantes.ERROR_NAO_ENCONTRADO;
import static com.livrosbiblicos.common.constante.TesteConstantes.MSG_LIVRO_NAO_ENCONTRADO;
import static com.livrosbiblicos.common.enums.ParametroEnum.PATH_PARAM;
import static com.livrosbiblicos.common.enums.TagEnum.*;
import static com.livrosbiblicos.common.enums.UrlEnum.LIVROS_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class DeletarLivrosTest extends BaseTestFuncional {

    @Autowired
    LivrosRepository livrosRepository;

    @Test
    @DisplayName("Deletar livro por id")
    void deveDeletarLivroPorId() throws Exception {

        String idLivro = buscarIdLivro();
        Long qtdAntes = livrosRepository.findById(idLivro).stream().count();

        getMvc().perform(delete(LIVROS_ID.getUrl().replace(PATH_PARAM.getParametro(), idLivro))
                        .contentType(APPLICATION_JSON))
                .andExpect(MATCHER_STATUS_IS_NO_CONTENT)
                .andDo(print());

        Long qtdDepois = livrosRepository.findById(idLivro).stream().count();

        assertThat(qtdAntes, is(1L));
        assertThat(qtdDepois, is(0L));
    }

    @Test
    @DisplayName("Não Deleta livro com id inválido")
    void NaoDeveDeletarLivroComIdInvalido() throws Exception {

        getMvc().perform(delete(LIVROS_ID.getUrl().replace(PATH_PARAM.getParametro(), "NAOEXISTE"))
                        .contentType(APPLICATION_JSON))
                .andExpect(MATCHER_STATUS_IS_NOT_FOUND)
                .andExpect(MATCHER_TAG_STATUS_CODE_IS_NOT_FOUND)
                .andExpect(jsonPath(TAG_ERROR.getTag(), is(ERROR_NAO_ENCONTRADO)))
                .andExpect(jsonPath(TAG_MESSAGE.getTag(), is(MSG_LIVRO_NAO_ENCONTRADO)))
                .andExpect(jsonPath(TAG_PATH.getTag(), containsString("NAOEXISTE")))
                .andDo(print());
    }
}