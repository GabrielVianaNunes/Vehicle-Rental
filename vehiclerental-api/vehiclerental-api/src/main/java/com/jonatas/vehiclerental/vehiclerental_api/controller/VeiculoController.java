package com.jonatas.vehiclerental.vehiclerental_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.vehiclerental.vehiclerental_api.model.Veiculo;
import com.jonatas.vehiclerental.vehiclerental_api.service.VeiculoService;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> listarTodos() {
        return veiculoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Veiculo> salvar(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.salvar(veiculo);
        return ResponseEntity.ok(novoVeiculo);
    }
}
