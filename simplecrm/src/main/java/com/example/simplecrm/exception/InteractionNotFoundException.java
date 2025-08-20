package com.example.simplecrm.exception;

public class InteractionNotFoundException extends RuntimeException {

    public InteractionNotFoundException(Long id) {
        super("📝Interaction not found with id: " + id);
    }
}
