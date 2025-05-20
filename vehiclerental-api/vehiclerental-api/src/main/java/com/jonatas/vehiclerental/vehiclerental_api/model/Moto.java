package com.jonatas.vehiclerental.vehiclerental_api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOTO")
public class Moto extends Veiculo {

    private boolean partidaEletrica;

    public boolean isPartidaEletrica() {
        return partidaEletrica;
    }

    public void setPartidaEletrica(boolean partidaEletrica) {
        this.partidaEletrica = partidaEletrica;
    }

    @Override
    public String getDescricaoDetalhada() {
        return "Moto: " + getModelo() + (partidaEletrica ? " com" : " sem") + " partida elétrica.";
    }
}
