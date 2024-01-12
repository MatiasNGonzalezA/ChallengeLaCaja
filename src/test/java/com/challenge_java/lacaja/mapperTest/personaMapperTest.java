package com.challenge_java.lacaja.mapperTest;
import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.service.mapper.IPersonaMapper;
import com.challenge_java.lacaja.service.mapper.IPersonaMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
public class personaMapperTest {

    @InjectMocks
    IPersonaMapper personaMapper = new IPersonaMapperImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    public void testPersonaAPersonaDTO_ConPersonaValida() {
        Persona persona = new Persona("Matias", 25, "Quilmes");
        persona.setId(1L);

        PersonaDTO personaDTO = personaMapper.personaAPersonaDTO(persona);

        assertEquals(persona.getId(), personaDTO.getId());
        assertEquals(persona.getNombre(), personaDTO.getNombre());
        assertEquals(persona.getEdad(), personaDTO.getEdad());
        assertEquals(persona.getCiudad(), personaDTO.getCiudad());
    }

    @Test
    public void testPersonaAPersonaDTO_ConPersonaNula() {
        PersonaDTO personaDTO = personaMapper.personaAPersonaDTO(null);

        assertNull(personaDTO);
    }

    @Test
    public void testPersonaDTOAPersona_ConPersonaDTOValida() {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(1L);
        personaDTO.setNombre("Matias");
        personaDTO.setEdad(25);
        personaDTO.setCiudad("Quilmes");

        Persona persona = personaMapper.personaDTOAPersona(personaDTO);

        assertNotNull(persona);
        assertEquals(personaDTO.getId(), persona.getId());
        assertEquals(personaDTO.getNombre(), persona.getNombre());
        assertEquals(personaDTO.getEdad(), persona.getEdad());
        assertEquals(personaDTO.getCiudad(), persona.getCiudad());
    }

    @Test
    public void testPersonaDTOAPersona_ConPersonaDTONula() {
        // Act
        Persona persona = personaMapper.personaDTOAPersona(null);

        // Assert
        assertNull(persona);
    }
}
