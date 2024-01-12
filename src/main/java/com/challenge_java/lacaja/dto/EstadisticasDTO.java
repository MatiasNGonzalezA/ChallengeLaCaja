package com.challenge_java.lacaja.dto;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
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

