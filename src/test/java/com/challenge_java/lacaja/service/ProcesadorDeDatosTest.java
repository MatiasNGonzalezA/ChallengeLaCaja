package com.challenge_java.lacaja.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.exceptions.CiudadVaciaException;
import com.challenge_java.lacaja.exceptions.EdadInvalidaException;
import com.challenge_java.lacaja.exceptions.NombreVacioException;
import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.repository.IPersonaRepository;
import com.challenge_java.lacaja.service.implement.ProcesadorDeDatosImplement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProcesadorDeDatosTest {

    @Mock
    private IPersonaRepository personaRepository;

    @InjectMocks
    private ProcesadorDeDatosImplement procesadorDeDatosImplement;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testProcesarDatosConRespuestaValida() {
        Persona persona = new Persona("Matias", 25, "Quilmes");
        PersonaDTO personaDT0 = new PersonaDTO(persona);

        ResponseEntity<Object> responseEntity = procesadorDeDatosImplement.procesarDatos(personaDT0);

        verify(personaRepository, times(1)).save(any());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }


    @Test
    void testProcesarDatosConNombreVacio() {
        Persona persona = new Persona("", 10,"Quilmes");
        PersonaDTO personaDTO = new PersonaDTO(persona);

        assertThrows(NombreVacioException.class, () -> {
            procesadorDeDatosImplement.procesarDatos(personaDTO);
        });
    }

    @Test
    void testProcesarDatosConEdadMenorAUno() {
        Persona persona = new Persona("Nombre", 0, "Ciudad");
        PersonaDTO personaDTO = new PersonaDTO(persona);

        assertThrows(EdadInvalidaException.class, () -> {
            procesadorDeDatosImplement.procesarDatos(personaDTO);
        });
    }
    @Test
    void testProcesarDatosConEdadMayorA150() {
        Persona persona = new Persona("Nombre", 160, "Ciudad");
        PersonaDTO personaDTO = new PersonaDTO(persona);

        assertThrows(EdadInvalidaException.class, () -> {
            procesadorDeDatosImplement.procesarDatos(personaDTO);
        });
    }


    @Test
    public void testProcesarDatosConCiudadVacia() {
        Persona persona = new Persona("Nombre", 25, "");
        PersonaDTO personaDTO = new PersonaDTO(persona);

        assertThrows(CiudadVaciaException.class, () -> {
            procesadorDeDatosImplement.procesarDatos(personaDTO);
        });
    }

    @Test
    public void testProcesarDatosConExcepcionIllegalArgumentException() {
        Persona persona = new Persona("Nombre", 25, "Quilmes");
        PersonaDTO personaDTO = new PersonaDTO(persona);
        doThrow(new IllegalArgumentException("Mensaje de error")).when(personaRepository).save(any());

        ResponseEntity<Object> responseEntity = procesadorDeDatosImplement.procesarDatos(personaDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Mensaje de error", responseEntity.getBody());
    }

}

