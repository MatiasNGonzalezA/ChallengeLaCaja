package com.challenge_java.lacaja.service;

import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.model.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {

    List<PersonaDTO> listaPersonasProcesadas();

    Optional<PersonaDTO> obtenerPersonaProcesada(Long id);

    EstadisticasDTO calcularEstadisticasEdadPersonas();

    String eliminarPersona(Long id);

    PersonaDTO actualizarPersona(Long id, Persona personaAct);
}
