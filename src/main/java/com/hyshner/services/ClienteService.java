package com.hyshner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyshner.domain.Cliente;
import com.hyshner.repositories.ClienteRepository;
import com.hyshner.services.exeptions.ObjectNotFoudException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoudException("Objeto não encontrado!: id"+ id + "-- tipo: " + Cliente.class.getName() );
		}
		return obj;
				
		
	}
	

}
