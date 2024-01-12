package com.challenge_java.lacaja.service.mapper;

import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.model.Persona;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IPersonaMapper {


    PersonaDTO personaAPersonaDTO(Persona persona);

    Persona personaDTOAPersona(PersonaDTO persona);
}


