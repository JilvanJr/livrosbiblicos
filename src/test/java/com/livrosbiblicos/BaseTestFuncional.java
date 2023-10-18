package com.livrosbiblicos;

import com.livrosbiblicos.domain.Livros;
import com.livrosbiblicos.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.livrosbiblicos.common.constante.TesteConstantes.NOME_GN;

@AutoConfigureMockMvc
@ActiveProfiles("funcional")
@AutoConfigureWireMock(port = 0, stubs = "classpath*:/mappings/**/*.json")
@SpringBootTest(classes = {Application.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaseTestFuncional {

    @Autowired
    private MockMvc mvc;

    @Autowired
    LivrosRepository livrosRepository;

    protected MockMvc getMvc() {
        return mvc;
    }

    protected String buscarIdLivro() {
        Optional<Livros> livroOptional = livrosRepository.findByNome(NOME_GN);
        String livro = livroOptional.get().getId();
        return livro;
    }
}