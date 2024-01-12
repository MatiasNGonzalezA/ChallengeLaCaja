package com.challenge_java.lacaja.controllerTest;
import com.challenge_java.lacaja.controller.PersonaController;
import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.service.IPersonaService;
import com.challenge_java.lacaja.service.mapper.IPersonaMapper;
import com.challenge_java.lacaja.service.mapper.IPersonaMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersonaControllerTest {

    @Mock
    private IPersonaService personaService;

    @InjectMocks
    private PersonaController personaController;

    @Mock
    private IPersonaMapper personaMapperMock;

    private IPersonaMapper personaMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        personaMapper = new IPersonaMapperImpl();
    }

    @Test
    public void testPersonasProcesadasListaDePersonasDTORespuestaValida() {
        Persona persona = new Persona( "Juan", 25, "Buenos Aires");
        PersonaDTO personadto  = personaMapper.personaAPersonaDTO(persona);
        Persona persona1 = new Persona( "Maria", 30, "Cordoba");
        PersonaDTO personadto1  = personaMapper.personaAPersonaDTO(persona1);


        when(personaMapperMock.personaAPersonaDTO(persona)).thenReturn(personadto);
        when(personaMapperMock.personaAPersonaDTO(persona1)).thenReturn(personadto1);
        when(personaService.listaPersonasProcesadas()).thenReturn(Arrays.asList(
                personadto,personadto1
        ));

        ResponseEntity<List<PersonaDTO>> response = personaController.personasProcesadas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testObtenerPersonaProcesadaExistente() {
        Long id = 1L;
        Persona persona = new Persona( "Juan", 25, "Buenos Aires");
        PersonaDTO personadto  = personaMapper.personaAPersonaDTO(persona);
        when(personaService.obtenerPersonaProcesada(id)).thenReturn(Optional.of(
                personadto
        ));

        ResponseEntity<Optional<PersonaDTO>> response = (ResponseEntity<Optional<PersonaDTO>>) personaController.personaProcesada(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Juan", response.getBody().get().getNombre());
    }

    @Test
    public void testEliminarPersonaRespuestaValida() {
        Long id = 1L;
        when(personaService.eliminarPersona(id)).thenReturn("Persona eliminada con éxito.");

        ResponseEntity<Object> response = personaController.eliminarPersona(id);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Persona eliminada con éxito.", response.getBody());

        verify(personaService, times(1)).eliminarPersona(id);
    }

    @Test
    public void testEstadisticasEdadPersonasRespuestaValida() {

        EstadisticasDTO estadisticasDTO = new EstadisticasDTO(10, 35);
        when(personaService.calcularEstadisticasEdadPersonas()).thenReturn(estadisticasDTO);

        ResponseEntity<EstadisticasDTO> response = personaController.estadisticasEdadPersonas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estadisticasDTO, response.getBody());

        verify(personaService, times(1)).calcularEstadisticasEdadPersonas();
    }

    @Test
    public void testActualizarPersonaRespuestaValida() {
        Long id = 1L;
        Persona personaEncontrada = new Persona("Antonio", 30, "Buenos Aires");
        personaEncontrada.setId(id);

        personaEncontrada.setNombre("Antonio Actualizado");
        personaEncontrada.setCiudad("Rosario");

        PersonaDTO personaEncontradaDTO = personaMapper.personaAPersonaDTO(personaEncontrada);

        when(personaService.actualizarPersona(id, personaEncontrada)).thenReturn(personaEncontradaDTO);

        ResponseEntity<Object> response = personaController.actualizarPersona(id, personaEncontrada);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(personaService, times(1)).actualizarPersona(id, personaEncontrada);
    }

}
