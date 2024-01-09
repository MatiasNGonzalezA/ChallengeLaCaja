package com.challenge_java.lacaja.controller;

import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.service.implement.ProcesadorDeDatosImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProcesarController {

    @Autowired
    private ProcesadorDeDatosImplement procesadorDeDatosImplement;


    @PostMapping("/procesar")
    public ResponseEntity<String> procesarDatos(@RequestBody PersonaDTO persona) {
        procesadorDeDatosImplement.procesarDatos(persona);
        return ResponseEntity.ok("Datos procesados y almacenados correctamente.");
    }
}
