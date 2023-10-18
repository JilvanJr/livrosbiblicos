package com.livrosbiblicos.controller;

import com.livrosbiblicos.domain.LivroDetalhes;
import com.livrosbiblicos.domain.Livros;
import com.livrosbiblicos.dto.LivrosDTO;
import com.livrosbiblicos.exception.ErrorResponse;
import com.livrosbiblicos.exception.ParametroInvalidoException;
import com.livrosbiblicos.services.LivrosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping(value = "/livros")
public class LivrosController {

    @Autowired
    private LivrosService service;

    @GetMapping
    @Operation(summary = "Lista todos os livros")
    public ResponseEntity<?> findAll(
            @RequestParam(value = "testamento", required = false) String testamento,
            @RequestParam(value = "autor", required = false) String autor,
            @RequestParam(value = "grupo", required = false) String grupo
    ) {
        try {
            // Valide os parâmetros
            service.validateParameters(testamento, autor, grupo);

            List<Livros> listaLivros = service.findByParameters(testamento, autor, grupo);
            List<LivrosDTO> listaLivrosDTO = listaLivros.stream().map(LivrosDTO::new).collect(Collectors.toList());

            return ResponseEntity.ok().body(listaLivrosDTO);
        } catch (ParametroInvalidoException e) {
            // Captura e trata exceções de parâmetros inválidos
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }



    @GetMapping("/{id}")
    @Operation(summary = "Lista os livros por id")
    public ResponseEntity<LivrosDTO> findById(@PathVariable String id) {
        Livros obj = service.findById(id);
        return ResponseEntity.ok().body(new LivrosDTO(obj));
    }

    @GetMapping("/{id}/detalhes")
    @Operation(summary = "Lista os detalhes dos livros por id")
    public ResponseEntity<List<LivroDetalhes>> findLivrosDetalhes(@PathVariable String id) {
        Livros obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getAutorId());
    }

    @PostMapping
    @Operation(summary = "Inseri novo livro")
    public ResponseEntity<Void> insert(@Valid @RequestBody LivrosDTO objDto) {
        Livros obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta livro por id")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera informações dos livros por id")
    public ResponseEntity<Void> update(@RequestBody LivrosDTO livrosDTO, @PathVariable String id) {
        Livros obj = service.fromDTO(livrosDTO);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }
}