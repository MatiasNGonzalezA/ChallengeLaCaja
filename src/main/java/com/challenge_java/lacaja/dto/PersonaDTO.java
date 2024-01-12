package com.challenge_java.lacaja.dto;

import com.challenge_java.lacaja.model.Persona;
import jakarta.persistence.Column;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PersonaDTO {
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede ser vacio")
    private String nombre;

    @Column(nullable = false )
    private Integer edad;

    @Column(nullable = false)
    @NotBlank(message = "La ciudad no puede ser vacio")
    private String ciudad;


    public PersonaDTO() {
    }

    public PersonaDTO(Persona persona) {
        this.id = persona.getId();
        this.nombre = persona.getNombre();
        this.edad = persona.getEdad();
        this.ciudad = persona.getCiudad();
    }

    @Override
    @Generated
    public String toString() {
        return (new ToStringBuilder(this)
                .append("id", id)
                .append("nombre", nombre)
                .append("edad", edad)
                .append("ciudad", ciudad)
                .toString());
    }

}
