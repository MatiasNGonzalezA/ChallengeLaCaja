package com.challenge_java.lacaja.repository;

import com.challenge_java.lacaja.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {

    @Query("SELECT AVG(p.edad) FROM Persona p")
    int calcularEdadPromedio();

}
