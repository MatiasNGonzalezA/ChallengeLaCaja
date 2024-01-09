package com.challenge_java.lacaja.service.implement;


import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.exceptions.CiudadVaciaException;
import com.challenge_java.lacaja.exceptions.EdadInvalidaException;
import com.challenge_java.lacaja.exceptions.NombreVacioException;
import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.repository.IPersonaRepository;
import com.challenge_java.lacaja.service.IProcesadorDeDatosService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProcesadorDeDatosImplement implements IProcesadorDeDatosService {

    @Autowired
    private IPersonaRepository personaRepository;


    @Override
    public ResponseEntity<Object> procesarDatos(PersonaDTO personaDTO) {
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

            Persona persona = new Persona();
            persona.setNombre(personaDTO.getNombre());
            persona.setEdad(personaDTO.getEdad());
            persona.setCiudad(personaDTO.getCiudad());

            personaRepository.save(persona);

            PersonaDTO newPersonaDTO = new PersonaDTO(persona);
            return new ResponseEntity<>(newPersonaDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }





}
