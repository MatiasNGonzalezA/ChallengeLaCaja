package com.challenge_java.lacaja.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
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

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


}
