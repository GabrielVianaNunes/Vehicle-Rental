package com.jonatas.vehiclerental.vehiclerental_api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatas.vehiclerental.vehiclerental_api.model.Aluguel;
import com.jonatas.vehiclerental.vehiclerental_api.model.Veiculo;
import com.jonatas.vehiclerental.vehiclerental_api.repository.AluguelRepository;
import com.jonatas.vehiclerental.vehiclerental_api.repository.VeiculoRepository;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Aluguel> listarTodos() {
        return aluguelRepository.findAll();
    }

    public List<Aluguel> listarAtivos() {
        return aluguelRepository.findByDataFimIsNull();
    }

    public Aluguel registrarAluguel(Aluguel aluguel) {
        Veiculo veiculo = aluguel.getVeiculo();

        if (!veiculo.isDisponivel()) {
            throw new IllegalArgumentException("Veículo indisponível para aluguel.");
        }

        if (aluguel.getDataInicio().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de início não pode ser no passado.");
        }

        // Atualiza a disponibilidade do veículo para false
        veiculo.setDisponivel(false);
        veiculoRepository.save(veiculo);

        return aluguelRepository.save(aluguel);
    }

    public void devolverVeiculo(Long aluguelId) {
        Aluguel aluguel = aluguelRepository.findById(aluguelId)
            .orElseThrow(() -> new IllegalArgumentException("Aluguel não encontrado"));

        if (aluguel.getDataFim() != null) {
            throw new IllegalStateException("Veículo já foi devolvido.");
        }

        aluguel.setDataFim(LocalDate.now());
        aluguel.getVeiculo().setDisponivel(true);

        veiculoRepository.save(aluguel.getVeiculo());
        aluguelRepository.save(aluguel);
    }
}
