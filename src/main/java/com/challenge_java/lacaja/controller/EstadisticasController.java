package com.challenge_java.lacaja.controller;

import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.service.IEstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EstadisticasController {

    @Autowired
    private IEstadisticasService estadisticasService;

    @GetMapping("/estadisticas")
    public ResponseEntity<EstadisticasDTO> obtenerEstadisticas() {
        EstadisticasDTO estadisticas = estadisticasService.calcularEstadisticas();
        return new ResponseEntity<>(estadisticas, HttpStatus.OK);
    }
}
