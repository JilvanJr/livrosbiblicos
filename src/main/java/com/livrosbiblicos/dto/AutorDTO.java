package com.livrosbiblicos.dto;

import com.livrosbiblicos.domain.Livros;

import java.io.Serializable;

public class AutorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String nome;

    public AutorDTO() {
    }

    public AutorDTO(Livros obj) {
        id = obj.getId();
        nome = obj.getNome();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}