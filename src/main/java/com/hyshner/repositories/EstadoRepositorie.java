package com.hyshner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyshner.domain.Estado;

@Repository
public interface EstadoRepositorie extends JpaRepository<Estado, Integer> {

}
