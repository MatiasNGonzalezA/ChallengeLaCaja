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
import com.challenge_java.lacaja.service.mapper.IPersonaMapper;
import com.challenge_java.lacaja.service.mapper.IPersonaMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class ProcesadorDeDatosTest {

    @Mock
    private IPersonaRepository personaRepository;

    @Mock
    private IPersonaMapper personaMapperMock;

    private IPersonaMapper personaMapper;

    @InjectMocks
    private ProcesadorDeDatosImplement procesadorDeDatosImplement;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        personaMapper = new IPersonaMapperImpl();
    }


    @Test
    public void testProcesarDatosConRespuestaValida() {
        Persona persona = new Persona("Matias", 25, "Quilmes");
        PersonaDTO personaDTO = personaMapper.personaAPersonaDTO(persona);
        when(personaMapperMock.personaDTOAPersona(personaDTO)).thenReturn(persona);
        when(personaMapperMock.personaAPersonaDTO(persona)).thenReturn(personaDTO);

        when(personaRepository.save(any())).thenReturn(persona);

        PersonaDTO response = procesadorDeDatosImplement.procesarDatos(personaDTO);

        verify(personaRepository, times(1)).save(any());
        assertNotNull(response);
        assertEquals(personaDTO.getNombre(), response.getNombre());
        assertEquals(personaDTO.getEdad(), response.getEdad());
        assertEquals(personaDTO.getCiudad(), response.getCiudad());
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

}

