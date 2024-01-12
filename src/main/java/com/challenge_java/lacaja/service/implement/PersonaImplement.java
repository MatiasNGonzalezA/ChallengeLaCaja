package com.challenge_java.lacaja.service.implement;

import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.exceptions.EdadInvalidaException;
import com.challenge_java.lacaja.exceptions.PersonaConIdNoEncontradoException;
import com.challenge_java.lacaja.exceptions.PersonaNoEncontradaException;
import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.repository.IPersonaRepository;
import com.challenge_java.lacaja.service.IPersonaService;
import com.challenge_java.lacaja.service.mapper.IPersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaImplement implements IPersonaService {

    @Autowired
    private IPersonaRepository personaRepository;

    @Autowired
    private IPersonaMapper personaMapper;

    @Override
    public List<PersonaDTO> listaPersonasProcesadas() {
        List<PersonaDTO> personasProcesadas = personaRepository.findAll().stream().map(persona -> personaMapper.personaAPersonaDTO(persona)).collect(Collectors.toList());

        if(personasProcesadas.isEmpty()){
            return personasProcesadas;
        }

        return personasProcesadas;
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaProcesada(Long id) {

        Persona personaEncontrada = personaRepository.findById(id)
                .orElseThrow(() -> new PersonaConIdNoEncontradoException("No se encontró la persona con el id: " + id));

        return Optional.ofNullable(personaMapper.personaAPersonaDTO(personaEncontrada));
    }

    @Override
    public PersonaDTO actualizarPersona(Long id, Persona personaAct) {
        Persona personaEncontrada = personaRepository.findById(id)
                .orElseThrow(() -> new PersonaNoEncontradaException("No se encontró el persona con el id: " + id));

        if (personaAct.getEdad() < 1 || personaAct.getEdad() > 150) {
            throw new EdadInvalidaException("La edad de la persona debe estar entre 1 y 150 años");
        }

        if (personaAct.getNombre() != null && !personaAct.getNombre().isEmpty()) {
            personaEncontrada.setNombre(personaAct.getNombre());
        }

        if (personaAct.getCiudad() != null && !personaAct.getCiudad().isEmpty()) {
            personaEncontrada.setCiudad(personaAct.getCiudad());
        }

        personaEncontrada.setEdad(personaAct.getEdad());

        personaRepository.save(personaEncontrada);

        return personaMapper.personaAPersonaDTO(personaEncontrada);
    }
    @Override
    public String eliminarPersona(Long id) {
        Optional<Persona> personaEncontrada = personaRepository.findById(id);

        if (personaEncontrada.isEmpty()) {
            throw new PersonaConIdNoEncontradoException("No se encontro la persona con el id: " + id);
        }

        personaRepository.deleteById(id);

        return "Persona eliminado con éxito.";
    }


    @Override
    public EstadisticasDTO calcularEstadisticasEdadPersonas() {
        long totalPersonas = personaRepository.count();
        int edadPromedio = personaRepository.calcularEdadPromedio();
        return new EstadisticasDTO(totalPersonas, edadPromedio);
    }



}
