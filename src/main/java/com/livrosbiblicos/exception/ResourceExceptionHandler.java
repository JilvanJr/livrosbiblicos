package com.livrosbiblicos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public ResponseEntity<StandardError> handleParametroInvalidoException(ParametroInvalidoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // Você pode escolher o código de status apropriado.
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Parâmetro Inválido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ParametroInvalidoException.TestamentoInvalidoException.class)
    public ResponseEntity<StandardError> handleTestamentoInvalido(ParametroInvalidoException.TestamentoInvalidoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Testamento inválido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ParametroInvalidoException.AutorInvalidoException.class)
    public ResponseEntity<StandardError> handleAutorInvalido(ParametroInvalidoException.AutorInvalidoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Autor inválido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ParametroInvalidoException.GrupoInvalidoException.class)
    public ResponseEntity<StandardError> handleGrupoInvalido(ParametroInvalidoException.GrupoInvalidoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Grupo inválido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}