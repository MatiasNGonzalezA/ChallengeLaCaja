package com.challenge_java.lacaja.service.implement;

import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.repository.IPersonaRepository;
import com.challenge_java.lacaja.service.IEstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;

public class EstadisticasImplement implements IEstadisticasService {

    @Autowired
    IPersonaRepository personaRepository;

    /** * Calcula estadísticas sobre las personas procesadas */
    @Override
    public EstadisticasDTO calcularEstadisticas() {
        long totalPersonas = personaRepository.count();
        int edadPromedio = personaRepository.calcularEdadPromedio();
        return new EstadisticasDTO(totalPersonas, edadPromedio);
    }
}
