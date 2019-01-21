package com.hyshner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyshner.domain.Produto;





@Repository
public interface ProdutoRepositorie extends JpaRepository<Produto, Integer> {

}
