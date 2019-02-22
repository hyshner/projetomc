package com.hyshner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hyshner.domain.Cliente;
import com.hyshner.dto.ClienteDTO;
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
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
		
	}

	public Cliente update(Cliente obj) {
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

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(
			Integer page, 
			Integer linesPerPage, 
			String orderBy,
			String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO (ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);
	}
}
