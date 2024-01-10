package com.challenge_java.lacaja.service;

import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.model.Persona;

import java.util.List;
import java.util.Optional;

public interface IProcesadorDeDatosService {
    PersonaDTO procesarDatos(PersonaDTO persona);



}
