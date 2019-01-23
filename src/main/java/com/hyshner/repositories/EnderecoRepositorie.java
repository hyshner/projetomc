package com.hyshner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyshner.domain.Endereco;

@Repository
public interface EnderecoRepositorie extends JpaRepository<Endereco, Integer> {

}
