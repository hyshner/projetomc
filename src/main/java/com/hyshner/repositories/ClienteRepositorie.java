package com.hyshner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyshner.domain.Cliente;

@Repository
public interface ClienteRepositorie extends JpaRepository<Cliente, Integer> {

}
