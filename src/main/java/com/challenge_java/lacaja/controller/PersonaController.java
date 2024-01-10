package com.challenge_java.lacaja.controller;

import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class PersonaController {

    @Autowired
    IPersonaService personaService;

    @GetMapping("/personas")
    public ResponseEntity<List<PersonaDTO>> personasProcesadas() {
        return new ResponseEntity<>(personaService.listaPersonasProcesadas(), HttpStatus.OK);
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<Optional<PersonaDTO>> personaProcesada(@PathVariable Long id) {
        return new ResponseEntity<>(personaService.obtenerPersonaProcesada(id), HttpStatus.OK);
    }

    @DeleteMapping("/personas/{id}")
    public ResponseEntity<Object> eliminarPersona(@PathVariable Long id) {
        return new ResponseEntity<>(personaService.eliminarEmpleado(id), HttpStatus.OK);
    }

    @GetMapping("/estadisticasPersonas")
    public ResponseEntity<EstadisticasDTO> estadisticasEdadPersonas() {
        EstadisticasDTO estadisticas = personaService.calcularEstadisticasEdadPersonas();
        return new ResponseEntity<>(estadisticas, HttpStatus.OK);
    }

    @PatchMapping("/empleados/{id}")
    public ResponseEntity<Object> actualizarPersona(@PathVariable Long id, @RequestBody Persona personaAct){

        return new ResponseEntity<>(personaService.actualizarPersona(id, personaAct), HttpStatus.OK);
    }


}
