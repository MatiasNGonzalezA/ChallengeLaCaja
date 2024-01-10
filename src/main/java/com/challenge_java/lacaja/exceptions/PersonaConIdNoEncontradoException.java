package com.challenge_java.lacaja.exceptions;

public class PersonaConIdNoEncontradoException extends RuntimeException{
    public PersonaConIdNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
