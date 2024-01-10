package com.challenge_java.lacaja.exceptions;

public class PersonaNoEncontradaException extends RuntimeException{
    public PersonaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
