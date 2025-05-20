package com.jonatas.vehiclerental.vehiclerental_api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARRO")
public class Carro extends Veiculo {

    private int portas;

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    @Override
    public String getDescricaoDetalhada() {
        return "Carro: " + getModelo() + " com " + portas + " portas.";
    }
}
