package com.livrosbiblicos.dto;

import com.livrosbiblicos.domain.Livros;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class LivrosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    @NotBlank(message = "O campo 'abreviacao' é obrigatório")
    @Size(min = 2, max = 2, message = "A 'abreviacao' deve ter 2 caracteres")
    private String abreviacao;
    @NotBlank(message = "O campo 'nome' é obrigatório")
    private String nome;
    @NotBlank(message = "O campo 'autor' é obrigatório")
    private String autor;
    @NotBlank(message = "O campo 'grupo' é obrigatório")
    private String grupo;
    @NotBlank(message = "O campo 'testamento' é obrigatório")
    @Size(min = 2, max = 2, message = "O 'testamento' deve ter 2 caracteres")
    private String testamento;

    public LivrosDTO() {
    }

    public LivrosDTO(Livros obj) {
        id = obj.getId();
        abreviacao = obj.getAbreviacao();
        nome = obj.getNome();
        autor = obj.getAutor();
        grupo = obj.getGrupo();
        testamento = obj.getTestamento();
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
}