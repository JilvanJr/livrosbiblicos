package com.livrosbiblicos.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "livros")
public class Livros implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String abreviacao;
    private String nome;
    private String autor;
    private String grupo;
    private String testamento;

    @DBRef
    private List<LivroDetalhes> autorId = new ArrayList<>();

    public Livros() {
    }

    public Livros(String id, String abreviacao, String nome, String autor, String grupo, String testamento) {
        this.id = id;
        this.abreviacao = abreviacao;
        this.nome = nome;
        this.autor = autor;
        this.grupo = grupo;
        this.testamento = testamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTestamento() {
        return testamento;
    }

    public void setTestamento(String testamento) {
        this.testamento = testamento;
    }

    public List<LivroDetalhes> getAutorId() {
        return autorId;
    }

    public void setAutorId(List<LivroDetalhes> autorId) {
        this.autorId = autorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livros livros = (Livros) o;
        return Objects.equals(id, livros.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}