package com.jonatas.vehiclerental.vehiclerental_api.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    private Veiculo veiculo;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    // Construtor padrão
    public Aluguel() {}

    public Aluguel(Cliente cliente, Veiculo veiculo, LocalDate dataInicio, LocalDate dataFim) {
        validarDatas(dataInicio, dataFim);
        validarDisponibilidade(veiculo);
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        veiculo.setDisponivel(false); // Marca o veículo como alugado
    }

    private void validarDatas(LocalDate inicio, LocalDate fim) {
        LocalDate hoje = LocalDate.now();
        if (inicio.isBefore(hoje)) {
            throw new IllegalArgumentException("A data de início não pode ser no passado.");
        }
        if (fim.isBefore(inicio) || fim.isEqual(inicio)) {
            throw new IllegalArgumentException("A data de fim deve ser após a data de início.");
        }
    }

    private void validarDisponibilidade(Veiculo veiculo) {
        if (!veiculo.isDisponivel()) {
            throw new IllegalStateException("O veículo selecionado não está disponível para aluguel.");
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public void encerrarAluguel() {
        this.dataFim = LocalDate.now();
        if (veiculo != null) {
            veiculo.setDisponivel(true); // Devolve o veículo
        }
    }

    // equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aluguel)) return false;
        Aluguel aluguel = (Aluguel) o;
        return Objects.equals(id, aluguel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
