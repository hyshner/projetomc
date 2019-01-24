package com.hyshner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyshner.domain.ItemPedido;;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
