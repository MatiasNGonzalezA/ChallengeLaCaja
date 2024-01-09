package com.challenge_java.lacaja.service;

import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.dto.PersonaDTO;
import org.springframework.http.ResponseEntity;

public interface IProcesadorDeDatosService {
    ResponseEntity<Object> procesarDatos(PersonaDTO persona);



}
