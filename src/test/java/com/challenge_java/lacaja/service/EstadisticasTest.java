package com.challenge_java.lacaja.service;

import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.repository.IPersonaRepository;
import com.challenge_java.lacaja.service.implement.PersonaImplement;
import com.challenge_java.lacaja.service.implement.ProcesadorDeDatosImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EstadisticasTest {

    @Mock
    private IPersonaRepository personaRepository;

    @InjectMocks
    private PersonaImplement personaImplement;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCalcularEstadisticas() {
        when(personaRepository.count()).thenReturn(10L);  //ejemplo, tenemos 10 personas en la base de datos
        when(personaRepository.calcularEdadPromedio()).thenReturn(30);  // y la edad promedio es de 30

        EstadisticasDTO estadisticasDTO = personaImplement.calcularEstadisticasPersonas();

        // Assert
        assertEquals(10L, estadisticasDTO.getTotalPersonas());
        assertEquals(30, estadisticasDTO.getEdadPromedio());
    }

    @Test
    public void testCalcularEstadisticasConBaseDeDatosVacia() {
        when(personaRepository.count()).thenReturn(0L);
        when(personaRepository.calcularEdadPromedio()).thenReturn(0);

        EstadisticasDTO estadisticasDTO = personaImplement.calcularEstadisticasPersonas();

        // Assert
        assertEquals(0L, estadisticasDTO.getTotalPersonas());
        assertEquals(0, estadisticasDTO.getEdadPromedio());
    }


}
