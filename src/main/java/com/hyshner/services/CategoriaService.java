package com.hyshner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyshner.domain.Categoria;
import com.hyshner.repositories.CategoriaRepositorie;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepositorie repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		return obj;
				
		
	}
	

}
