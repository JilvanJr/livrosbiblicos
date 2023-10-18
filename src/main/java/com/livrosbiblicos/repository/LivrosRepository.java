package com.livrosbiblicos.repository;

import com.livrosbiblicos.domain.Livros;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivrosRepository extends MongoRepository<Livros, String> {

    List<Livros> findByTestamentoAndAutorContainingIgnoreCaseAndGrupoContainingIgnoreCase(
            String testamento, String autor, String grupo
    );

    List<Livros> findByTestamentoAndAutorContainingIgnoreCase(String testamento, String autor);

    List<Livros> findByTestamentoAndGrupoContainingIgnoreCase(String testamento, String grupo);

    List<Livros> findByAutorContainingIgnoreCaseAndGrupoContainingIgnoreCase(String autor, String grupo);

    List<Livros> findByTestamento(String testamento);

    List<Livros> findByAutorContainingIgnoreCase(String autor);

    List<Livros> findByGrupoContainingIgnoreCase(String grupo);

    Optional<Livros> findByNome(String nome);
}