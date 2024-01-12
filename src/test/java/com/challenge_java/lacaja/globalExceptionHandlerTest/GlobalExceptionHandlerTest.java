package com.challenge_java.lacaja.globalExceptionHandlerTest;

import com.challenge_java.lacaja.exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void testHandleNombreVacioException() {
        NombreVacioException exception = new NombreVacioException("El campo nombre no puede estar vacío");
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleNombreVacioException(exception);

        assertEquals("El campo nombre no puede estar vacío", responseEntity.getBody());
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testHandleEdadInvalidaException() {
        EdadInvalidaException exception = new EdadInvalidaException("La edad no puede ser menor a 1");
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleEdadInvalidaException(exception);

        assertEquals("La edad no puede ser menor a 1", responseEntity.getBody());
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testHandleCiudadVaciaException() {
        CiudadVaciaException exception = new CiudadVaciaException("El campo ciudad no puede estar vacío");
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleCiudadVaciaException(exception);

        assertEquals("El campo ciudad no puede estar vacío", responseEntity.getBody());
        assertEquals(400, responseEntity.getStatusCodeValue());
    }


    @Test
    void testHandlePersonaConIdNoEncontradoException() {
        PersonaConIdNoEncontradoException ex = new PersonaConIdNoEncontradoException("Persona no encontrada");

        ResponseEntity<String> response = globalExceptionHandler.handlePersonaConIdNoEncontradoException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Persona no encontrada", response.getBody());
    }

    @Test
    void testHandlePersonaNoEncontradaException() {
        PersonaNoEncontradaException ex = new PersonaNoEncontradaException("Persona no encontrada");

        ResponseEntity<String> response = globalExceptionHandler.handlePersonaNoEncontradaException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Persona no encontrada", response.getBody());
    }
}

