package com.livrosbiblicos.exception;

import java.util.ArrayList;
import java.util.List;

public class CustomException extends RuntimeException {
    private List<String> errorMessages;

    public CustomException(String message) {
        super(message);
        this.errorMessages = new ArrayList<>(); // Inicialize a lista
        errorMessages.add(message); // Adicione a mensagem ao campo
    }

    public CustomException(List<String> messages) {
        super(messages.toString());
        this.errorMessages = new ArrayList<>(messages); // Copie a lista
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
