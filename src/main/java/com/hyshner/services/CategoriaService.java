package com.hyshner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyshner.domain.Categoria;
import com.hyshner.repositories.CategoriaRepository;
import com.hyshner.services.exeptions.ObjectNotFoudException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoudException("Objeto não encontrado!: id"+ id + "-- tipo: " + Categoria.class.getName() );
		}
		return obj;
					


}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
		
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
}
