package com.challenge_java.lacaja.controller;

import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.service.IProcesadorDeDatosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProcesarController {

    @Autowired
    private IProcesadorDeDatosService procesadorDeDatosService;


    @PostMapping("/procesar")
    public ResponseEntity<String> procesarDatos(@RequestBody PersonaDTO persona) {
            procesadorDeDatosService.procesarDatos(persona);
            return ResponseEntity.ok("Datos procesados y almacenados correctamente.");
    }



}
