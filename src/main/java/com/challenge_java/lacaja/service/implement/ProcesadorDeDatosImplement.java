package com.challenge_java.lacaja.service.implement;


import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.exceptions.CiudadVaciaException;
import com.challenge_java.lacaja.exceptions.EdadInvalidaException;
import com.challenge_java.lacaja.exceptions.NombreVacioException;
import com.challenge_java.lacaja.exceptions.PersonaNoEncontradaException;
import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.repository.IPersonaRepository;
import com.challenge_java.lacaja.service.IProcesadorDeDatosService;
import com.challenge_java.lacaja.service.mapper.IPersonaMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProcesadorDeDatosImplement implements IProcesadorDeDatosService {

    @Autowired
    private IPersonaRepository personaRepository;

    @Autowired
    private IPersonaMapper personaMapper;


    @Override
    public PersonaDTO procesarDatos(PersonaDTO personaDTO) {
        try {
            if (Strings.isBlank(personaDTO.getNombre())) {
                throw new NombreVacioException("El campo nombre no puede estar vacío");
            }
            if (personaDTO.getEdad() < 1) {
                throw new EdadInvalidaException("La edad no puede ser menor a 1");
            }
            if (personaDTO.getEdad() > 150) {
                throw new EdadInvalidaException("La edad no puede ser mayor a 150 años");

            }
            if (Strings.isBlank(personaDTO.getCiudad())) {
                throw new CiudadVaciaException("El campo ciudad no puede estar vacío");
            }

            var persona =  personaRepository.save(personaMapper.personaDTOAPersona(personaDTO));

            return personaMapper.personaAPersonaDTO(persona);
        } catch (IllegalArgumentException e) {
             throw e;
        }
    }



}
