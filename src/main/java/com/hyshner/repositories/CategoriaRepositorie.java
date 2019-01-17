package com.hyshner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyshner.domain.Categoria;

@Repository
public interface CategoriaRepositorie extends JpaRepository<Categoria, Integer> {

}
