package com.hyshner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyshner.domain.Pedido;
import com.hyshner.repositories.PedidoRepository;
import com.hyshner.services.exeptions.ObjectNotFoudException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoudException("Objeto não encontrado!: id"+ id + "-- tipo: " + Pedido.class.getName() );
		}
		return obj;
				
		
	}
	

}
