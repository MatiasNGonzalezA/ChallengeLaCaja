package com.challenge_java.lacaja.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name="native", strategy = "native")
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="edad")
    private Integer edad;

    @Column(name="ciudad")
    private String ciudad;


    public Persona() {
    }

    public Persona(String nombre, Integer edad, String ciudad) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
    }


}
