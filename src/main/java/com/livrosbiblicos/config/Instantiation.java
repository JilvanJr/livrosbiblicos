package com.livrosbiblicos.config;

import com.livrosbiblicos.domain.LivroDetalhes;
import com.livrosbiblicos.domain.Livros;
import com.livrosbiblicos.dto.AutorDTO;
import com.livrosbiblicos.repository.LivroDetalhesRepository;
import com.livrosbiblicos.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
@Profile("!test") // Executar apenas quando o perfil não for "test"
public class Instantiation implements CommandLineRunner {

    @Autowired
    private LivrosRepository livrosRepository;

    @Autowired
    private LivroDetalhesRepository livroDetalhesRepository;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void run(String... args) throws Exception {

        livrosRepository.deleteAll();
        livroDetalhesRepository.deleteAll();

        // VT
        Livros genesis = new Livros(null, "Gn", "Gênesis", "Móises", "Pentateuco", "VT");
        Livros exodo = new Livros(null, "Êx", "Êxodo", "Móises", "Pentateuco", "VT");
        Livros levitico = new Livros(null, "Lv", "Levítico", "Móises", "Pentateuco", "VT");
        // NT
        Livros mateus = new Livros(null, "Mt", "Mateus", "Mateus", "Evangelhos", "NT");
        Livros marcos = new Livros(null, "Mc", "Marcos", "Marcos", "Evangelhos", "NT");
        Livros romanos = new Livros(null, "Rm", "Romanos", "Paulo", "Cartas de Paulo", "NT");

        livrosRepository.saveAll(Arrays.asList(genesis, exodo, levitico, mateus, marcos, romanos));

        // VT
        LivroDetalhes gnDetalhes = new LivroDetalhes(null, 50, 1533, true, new AutorDTO(genesis));
        LivroDetalhes exDetalhes = new LivroDetalhes(null, 40, 1213, true, new AutorDTO(exodo));
        LivroDetalhes lvDetalhes = new LivroDetalhes(null, 27, 859, true, new AutorDTO(levitico));
        // NT
        LivroDetalhes mtDetalhes = new LivroDetalhes(null, 28, 1071, true, new AutorDTO(mateus));
        LivroDetalhes mcDetalhes = new LivroDetalhes(null, 16, 678, false, new AutorDTO(marcos));
        LivroDetalhes rmDetalhes = new LivroDetalhes(null, 16, 433, false, new AutorDTO(romanos));

        livroDetalhesRepository.saveAll(Arrays.asList(gnDetalhes, exDetalhes, lvDetalhes, mtDetalhes, mcDetalhes, rmDetalhes));

        genesis.getAutorId().addAll(Arrays.asList(gnDetalhes));
        livrosRepository.save(genesis);

        mateus.getAutorId().addAll(Arrays.asList(mtDetalhes));
        livrosRepository.save(mateus);
    }
}