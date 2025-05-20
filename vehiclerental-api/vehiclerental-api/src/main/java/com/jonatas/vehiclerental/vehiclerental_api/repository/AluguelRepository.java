package com.jonatas.vehiclerental.vehiclerental_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonatas.vehiclerental.vehiclerental_api.model.Aluguel;
import com.jonatas.vehiclerental.vehiclerental_api.model.Veiculo;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    List<Aluguel> findByVeiculoAndDataFimAfter(Veiculo veiculo, LocalDate data);

    List<Aluguel> findByDataFimAfter(LocalDate hoje);

    List<Aluguel> findByDataFimIsNull();
}
