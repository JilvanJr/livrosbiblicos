package com.livrosbiblicos.exception;

public class ParametroInvalidoException extends RuntimeException {

    public ParametroInvalidoException(String msg) {
        super(msg);
    }

    public static class TestamentoInvalidoException extends RuntimeException {
        public TestamentoInvalidoException(String message) {
            super(message);
        }
    }

    // Exception para erro de Autor inválido
    public static class AutorInvalidoException extends RuntimeException {
        public AutorInvalidoException(String message) {
            super(message);
        }
    }

    // Exception para erro de Grupo inválido
    public static class GrupoInvalidoException extends RuntimeException {
        public GrupoInvalidoException(String message) {
            super(message);
        }
    }
}