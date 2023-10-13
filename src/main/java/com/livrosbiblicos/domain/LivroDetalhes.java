package com.livrosbiblicos.domain;

import com.livrosbiblicos.dto.AutorDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "livro_detalhes")
public class LivroDetalhes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private Integer qtd_capitulos;
    private Integer qtd_versiculos;
    private Boolean mais20_cap;
    private AutorDTO livro_id;

    public LivroDetalhes() {
    }

    public LivroDetalhes(String id, Integer qtd_capitulos, Integer qtd_versiculos, Boolean mais20_cap, AutorDTO livro_id) {
        this.id = id;
        this.qtd_capitulos = qtd_capitulos;
        this.qtd_versiculos = qtd_versiculos;
        this.mais20_cap = mais20_cap;
        this.livro_id = livro_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQtd_capitulos() {
        return qtd_capitulos;
    }

    public void setQtd_capitulos(Integer qtd_capitulos) {
        this.qtd_capitulos = qtd_capitulos;
    }

    public Integer getQtd_versiculos() {
        return qtd_versiculos;
    }

    public void setQtd_versiculos(Integer qtd_versiculos) {
        this.qtd_versiculos = qtd_versiculos;
    }

    public Boolean getMais20_cap() {
        return mais20_cap;
    }

    public void setMais20_cap(Boolean mais20_cap) {
        this.mais20_cap = mais20_cap;
    }

    public AutorDTO getLivro_id() {
        return livro_id;
    }

    public void setLivro_id(AutorDTO livro_id) {
        this.livro_id = livro_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroDetalhes that = (LivroDetalhes) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}