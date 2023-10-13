package com.livrosbiblicos.services;

import com.livrosbiblicos.domain.Livros;
import com.livrosbiblicos.dto.LivrosDTO;
import com.livrosbiblicos.exception.ObjectNotFoundException;
import com.livrosbiblicos.exception.ParametroInvalidoException;
import com.livrosbiblicos.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {

    @Autowired
    private LivrosRepository repo;

    public List<Livros> findAll() {
        return repo.findAll();
    }

    public Livros findById(String id) {
        Optional<Livros> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado"));
    }

    public List<Livros> findByParameters(String testamento, String autor, String grupo) {
        if (testamento != null && autor != null && grupo != null) {
            return repo.findByTestamentoAndAutorContainingIgnoreCaseAndGrupoContainingIgnoreCase(
                    testamento, autor, grupo
            );
        } else if (testamento != null && autor != null) {
            return repo.findByTestamentoAndAutorContainingIgnoreCase(testamento, autor);
        } else if (testamento != null && grupo != null) {
            return repo.findByTestamentoAndGrupoContainingIgnoreCase(testamento, grupo);
        } else if (autor != null && grupo != null) {
            return repo.findByAutorContainingIgnoreCaseAndGrupoContainingIgnoreCase(autor, grupo);
        } else if (testamento != null) {
            return repo.findByTestamento(testamento);
        } else if (autor != null) {
            return repo.findByAutorContainingIgnoreCase(autor);
        } else if (grupo != null) {
            return repo.findByGrupoContainingIgnoreCase(grupo);
        } else {
            return repo.findAll();
        }
    }

    public Livros insert(Livros obj) {
        return repo.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public Livros update(Livros obj) {
        Livros newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Livros newObj, Livros obj) {
        newObj.setAbreviacao(obj.getAbreviacao());
        newObj.setNome(obj.getNome());
        newObj.setAutor(obj.getAutor());
        newObj.setGrupo(obj.getGrupo());
        newObj.setTestamento(obj.getTestamento());
    }

    public Livros fromDTO(LivrosDTO objDto) {
        return new Livros(
                objDto.getId(),
                objDto.getAbreviacao(),
                objDto.getNome(),
                objDto.getAutor(),
                objDto.getGrupo(),
                objDto.getTestamento());
    }

    public void validateParameters(String testamento, String autor, String grupo) {
        if (testamento != null && !testamento.equals("VT") && !testamento.equals("NT")) {
            throw new ParametroInvalidoException.TestamentoInvalidoException("O parâmetro 'testamento' é inválido. Deve ser 'VT' ou 'NT'.");
        }

        if (autor != null && autor.length() < 3) {
            throw new ParametroInvalidoException.AutorInvalidoException("O parâmetro 'autor' é inválido. Deve conter pelo menos 3 caracteres.");
        }

        if (grupo != null && grupo.length() < 3) {
            throw new ParametroInvalidoException.GrupoInvalidoException("O parâmetro 'grupo' é inválido. Deve conter pelo menos 3 caracteres.");
        }
    }
}