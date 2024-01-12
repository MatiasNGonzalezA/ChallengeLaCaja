package com.challenge_java.lacaja.controllerTest;
import com.challenge_java.lacaja.controller.ProcesarController;
import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.service.IProcesadorDeDatosService;
import com.challenge_java.lacaja.service.mapper.IPersonaMapper;
import com.challenge_java.lacaja.service.mapper.IPersonaMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class ProcesarPersonaControllerTest {

    @Mock
    private IProcesadorDeDatosService procesadorDeDatosService;

    @InjectMocks
    private ProcesarController procesarController;

    @Mock
    private IPersonaMapper personaMapperMock;

    private IPersonaMapper personaMapper;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        personaMapper = new IPersonaMapperImpl();
    }

    @Test
    public void testProcesarDatosRespuestaValida() {
        Persona persona = new Persona("Antonio", 30, "Buenos Aires");
        PersonaDTO personaDTO = personaMapperMock.personaAPersonaDTO(persona); // Utiliza personaMapperMock aquí

        // Cambia doNothing() a thenReturn()
        when(personaMapperMock.personaAPersonaDTO(persona)).thenReturn(personaDTO);
        when(procesadorDeDatosService.procesarDatos(personaDTO)).thenReturn(personaDTO);

        // Ejecutar el método del controlador
        ResponseEntity<String> response = procesarController.procesarDatos(personaDTO);

        // Verificar el resultado y el código de estado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Datos procesados y almacenados correctamente.", response.getBody());

        // Verificar que el método del servicio fue llamado
        verify(procesadorDeDatosService, times(1)).procesarDatos(personaDTO);


    }
}
