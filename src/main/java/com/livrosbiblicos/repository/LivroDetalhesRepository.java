package com.livrosbiblicos.repository;

import com.livrosbiblicos.domain.LivroDetalhes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroDetalhesRepository extends MongoRepository<LivroDetalhes, String> {

}