package com.livrosbiblicos.resources;

import com.livrosbiblicos.domain.LivroDetalhes;
import com.livrosbiblicos.domain.Livros;
import com.livrosbiblicos.dto.LivrosDTO;
import com.livrosbiblicos.exception.ErrorResponse;
import com.livrosbiblicos.exception.ParametroInvalidoException;
import com.livrosbiblicos.services.LivrosService;
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
public class LivrosResource {

    @Autowired
    private LivrosService service;

    @GetMapping
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
    public ResponseEntity<LivrosDTO> findById(@PathVariable String id) {
        Livros obj = service.findById(id);
        return ResponseEntity.ok().body(new LivrosDTO(obj));
    }

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<List<LivroDetalhes>> findLivrosDetalhes(@PathVariable String id) {
        Livros obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getAutorId());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody LivrosDTO objDto) {
        Livros obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody LivrosDTO livrosDTO, @PathVariable String id) {
        Livros obj = service.fromDTO(livrosDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
}