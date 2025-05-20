package com.jonatas.vehiclerental.vehiclerental_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.vehiclerental.vehiclerental_api.model.Aluguel;
import com.jonatas.vehiclerental.vehiclerental_api.service.AluguelService;

@RestController
@RequestMapping("/api/alugueis")
@CrossOrigin(origins = "*")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public List<Aluguel> listarTodos() {
        return aluguelService.listarTodos();
    }

    @GetMapping("/ativos")
    public List<Aluguel> listarAtivos() {
        return aluguelService.listarAtivos();
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Aluguel aluguel) {
        try {
            Aluguel novoAluguel = aluguelService.registrarAluguel(aluguel);
            return ResponseEntity.ok(novoAluguel);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<?> devolver(@PathVariable Long id) {
        try {
            aluguelService.devolverVeiculo(id);
            return ResponseEntity.ok("Veículo devolvido com sucesso.");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
