package com.jonatas.vehiclerental.vehiclerental_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonatas.vehiclerental.vehiclerental_api.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);
}
