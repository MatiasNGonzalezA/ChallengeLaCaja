package com.challenge_java.lacaja.serviceTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.exceptions.EdadInvalidaException;
import com.challenge_java.lacaja.exceptions.PersonaConIdNoEncontradoException;
import com.challenge_java.lacaja.exceptions.PersonaNoEncontradaException;
import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.repository.IPersonaRepository;
import com.challenge_java.lacaja.service.implement.PersonaImplement;
import com.challenge_java.lacaja.service.mapper.IPersonaMapper;
import com.challenge_java.lacaja.service.mapper.IPersonaMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class PersonaTest {

    @Mock
    private IPersonaRepository personaRepository;

    @Mock
    private IPersonaMapper personaMapperMock;


    @InjectMocks
    private PersonaImplement personaImplement;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerListaPersonas() {
        Persona persona1 = new Persona("Matias", 25, "Quilmes");
        Persona persona2 = new Persona("Jose", 30, "Merlo");
        when(personaRepository.findAll()).thenReturn(Arrays.asList(persona1, persona2));

        List<PersonaDTO> listaPersonas = personaImplement.listaPersonasProcesadas();

        assertNotNull(listaPersonas);
        assertFalse(listaPersonas.isEmpty());
        assertEquals(2, listaPersonas.size());
    }
    @Test
    public void testObtenerListaVacia() {
        when(personaRepository.findAll()).thenReturn(Collections.emptyList());

        List<PersonaDTO> listaPersonas = personaImplement.listaPersonasProcesadas();

        assertNotNull(listaPersonas);
        assertTrue(listaPersonas.isEmpty());
    }

    @Test
    public void testObtenerPersonaProcesada(){
        Persona persona = new Persona("Matias", 25, "Quilmes");
        persona.setId(1L);
        PersonaDTO personaDTO = personaMapperMock.personaAPersonaDTO(persona);
        when(personaRepository.findById(persona.getId())).thenReturn(Optional.of(persona));
        when(personaMapperMock.personaDTOAPersona(personaDTO)).thenReturn(persona);
        when(personaMapperMock.personaAPersonaDTO(persona)).thenReturn(personaDTO);
        Optional<PersonaDTO> personaEncontrada = personaImplement.obtenerPersonaProcesada(persona.getId());

        assertNotNull(personaDTO);
        assertEquals(1L, personaEncontrada.get().getId());
    }

//    @Test
//    public void testActualizarPersonaExitoso() {
//        // Datos de prueba
//        Long id = 1L;
//        Persona personaEncontrada = new Persona("Antonio", 30, "Buenos Aires");
//        personaEncontrada.setId(id);
//
//        Persona personaAct = new Persona("Antonio Actualizado", 35, "Rosario");
//
//        PersonaDTO personaEncontradaDTO = personaMapper.personaAPersonaDTO(personaAct);
//
//
//        // Configuración de mock
//        when(personaRepository.findById(any())).thenReturn(Optional.of(personaEncontrada));
//        when(personaMapper.personaAPersonaDTO(personaEncontrada)).thenReturn(personaEncontradaDTO);
//
//        // Llamada al método que se va a probar
//        PersonaDTO resultado = personaImplement.actualizarPersona(id, personaAct);
//
//        // Verificaciones
//        assertNotNull(resultado);
//        assertEquals(personaAct.getNombre(), resultado.getNombre());
//        assertEquals(personaAct.getEdad(), resultado.getEdad());
//        assertEquals(personaAct.getCiudad(), resultado.getCiudad());
//
//        // Verificar que se llamó al método save del repository una vez
//        verify(personaRepository, times(1)).save(any());
//    }
    @Test
    public void testActualizarPersonaExitoso() {
        Long id = 1L;
        Persona personaEncontrada = new Persona("Antonio", 30, "Buenos Aires");
        personaEncontrada.setId(id);

        Persona personaAct = new Persona("Antonio Actualizado", 35, "Rosario");
        personaEncontrada = personaAct;
        PersonaDTO personaActDTO = personaMapperMock.personaAPersonaDTO(personaAct);

        when(personaRepository.findById(id)).thenReturn(Optional.of(personaEncontrada));
        when(personaMapperMock.personaAPersonaDTO(personaAct)).thenReturn(personaActDTO);

        PersonaDTO resultado = personaImplement.actualizarPersona(id, personaAct);

        assertNotNull(resultado);
        assertEquals(personaAct.getNombre(), resultado.getNombre());
        assertEquals(personaAct.getEdad(), resultado.getEdad());
        assertEquals(personaAct.getCiudad(), resultado.getCiudad());

        verify(personaRepository, times(1)).save(personaAct);
    }



    @Test
    public void testActualizarPersonaDatosVacios() {
        Long id = 1L;
        Persona personaEncontrada = new Persona("Antonio", 30, "Buenos Aires");
        personaEncontrada.setId(id);

        Persona personaAct = new Persona("", 35, "");
        personaEncontrada = personaAct;
        PersonaDTO personaActDTO = personaMapperMock.personaAPersonaDTO(personaAct);

        when(personaRepository.findById(id)).thenReturn(Optional.of(personaEncontrada));
        when(personaMapperMock.personaAPersonaDTO(personaAct)).thenReturn(personaActDTO);

        PersonaDTO resultado = personaImplement.actualizarPersona(id, personaAct);

        assertNotNull(resultado);
        assertEquals(personaEncontrada.getNombre(), resultado.getNombre());
        assertEquals(personaAct.getEdad(), resultado.getEdad());
        assertEquals(personaEncontrada.getCiudad(), resultado.getCiudad());

        verify(personaRepository, times(1)).save(personaAct);
    }


    @Test
    public void testActualizarPersonaConEdadMenorA1() {
        // Datos de prueba
        Long id = 1L;
        Persona personaExistente = new Persona("Antonio", 30, "Buenos Aires");
        Persona personaConEdadInvalida = new Persona("Antonio Actualizado", -5, "Rosario");

        // Configuración del mock
        when(personaRepository.findById(id)).thenReturn(Optional.of(personaExistente));

        assertThrows(EdadInvalidaException.class, () -> {
            personaImplement.actualizarPersona(id, personaConEdadInvalida);
        });

    }

    @Test
    public void testActualizarPersonaConEdadMayorA150() {
        // Datos de prueba
        Long id = 1L;
        Persona personaExistente = new Persona("Antonio", 30, "Buenos Aires");
        Persona personaConEdadInvalida = new Persona("Antonio Actualizado", 155, "Rosario");

        // Configuración del mock
        when(personaRepository.findById(id)).thenReturn(Optional.of(personaExistente));

        assertThrows(EdadInvalidaException.class, () -> {
            personaImplement.actualizarPersona(id, personaConEdadInvalida);
        });

    }

    @Test
    public void testActualizarPersonaNoEncontrada() {
        Persona personaExistente = new Persona("Antonio", 30, "Buenos Aires");
        personaExistente.setId(1L);

        when(personaRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(PersonaNoEncontradaException.class, () -> {
            personaImplement.actualizarPersona(any(), new Persona());
        });
    }


    @Test
    public void testEliminarEmpleadoExitoso(){
        Persona personaAEliminar = new Persona("Antonio", 30, "Buenos Aires");
        personaAEliminar.setId(1L);

        when(personaRepository.findById(personaAEliminar.getId())).thenReturn(Optional.of(personaAEliminar));

        String resultado = personaImplement.eliminarPersona(personaAEliminar.getId());

        assertNotNull(resultado);
        assertEquals("Persona eliminado con éxito.", resultado);
    }

    @Test
    public void testEliminarEmpleadoFallido(){
        Persona personaAEliminar = new Persona("Antonio", 30, "Buenos Aires");
        personaAEliminar.setId(1L);

        when(personaRepository.findById(personaAEliminar.getId())).thenReturn(Optional.empty());

        assertThrows(PersonaConIdNoEncontradoException.class, () -> {
            personaImplement.eliminarPersona(personaAEliminar.getId());
        });







    }



}
