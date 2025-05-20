package com.jonatas.vehiclerental.vehiclerental_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatas.vehiclerental.vehiclerental_api.model.Veiculo;
import com.jonatas.vehiclerental.vehiclerental_api.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void excluir(Long id) {
        veiculoRepository.deleteById(id);
    }

    public void atualizarDisponibilidade(Long id, boolean disponivel) {
        veiculoRepository.findById(id).ifPresent(veiculo -> {
            veiculo.setDisponivel(disponivel);
            veiculoRepository.save(veiculo);
        });
    }
}
