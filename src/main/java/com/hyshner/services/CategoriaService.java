package com.hyshner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.hyshner.domain.Categoria;
import com.hyshner.dto.CategoriaDTO;
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

	public void delete(Integer id) {
		find(id);
		try {
		repo.delete(id);
		}
		catch(DataIntegrityViolationException x) {
			throw new com.hyshner.services.exeptions.DataIntegrityViolationException("Não é possivel excluir uma categoria com produtos");
		}
		
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(
			Integer page, 
			Integer linesPerPage, 
			String orderBy,
			String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO (CategoriaDTO objDTO) {
		return new Categoria( objDTO.getId(),objDTO.getNome());
	}
}
