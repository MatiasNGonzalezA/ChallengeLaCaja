package com.challenge_java.lacaja.dto;

public class EstadisticasDTO {

    private long totalPersonas;
    private int edadPromedio;

    public EstadisticasDTO(long totalPersonas, int edadPromedio) {
        this.totalPersonas = totalPersonas;
        this.edadPromedio = edadPromedio;
    }

    public long getTotalPersonas() {
        return totalPersonas;
    }

    public int getEdadPromedio() {
        return edadPromedio;
    }
}

