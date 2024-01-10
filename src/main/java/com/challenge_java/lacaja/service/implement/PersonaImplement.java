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
        Optional<PersonaDTO> personasProcesada =personaRepository.findById(id).map(persona -> personaMapper.personaAPersonaDTO(persona));

        return personasProcesada;
    }

    @Override
    public PersonaDTO actualizarPersona(Long id,Persona personaAct){

        Persona personaEncontrada = personaRepository.findById(id).orElse(null);

        if (personaEncontrada == null) {
            throw new PersonaNoEncontradaException("No se encontro el empleado con el id: " + id);
        }

        if (personaAct.getEdad() < 1) {
            throw new EdadInvalidaException("La edad del empleado no puede ser menor a 1 año");
        }

        if (personaAct.getEdad() > 150) {
            throw new EdadInvalidaException("La edad del empleado no puede ser mayor a 150 años");
        }

        personaEncontrada.setNombre(personaAct.getNombre());
        personaEncontrada.setEdad(personaAct.getEdad());
        personaEncontrada.setCiudad(personaAct.getCiudad());


        personaRepository.save(personaEncontrada);


        return personaMapper.personaAPersonaDTO(personaEncontrada);
    }

    @Override
    public String eliminarEmpleado(Long id) {
        Optional<Persona> empleado = personaRepository.findById(id);

        if (empleado.isEmpty()) {
            throw new PersonaConIdNoEncontradoException("No se encontro el empleado con el id: " + id);
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
