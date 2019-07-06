package com.hyshner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hyshner.domain.Cidade;
import com.hyshner.domain.Cliente;
import com.hyshner.domain.Endereco;
import com.hyshner.domain.enums.TipoCliente;
import com.hyshner.dto.ClienteDTO;
import com.hyshner.dto.ClienteNewDTO;
import com.hyshner.repositories.ClienteRepository;
import com.hyshner.repositories.EnderecoRepository;
import com.hyshner.services.exeptions.ObjectNotFoudException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository endRepo;
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoudException("Objeto não encontrado!: id"+ id + "-- tipo: " + Cliente.class.getName() );
		}
		return obj;
				
		
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		endRepo.save(obj.getEnderecos());
		return obj;
		
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData (newObj,obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.delete(id);
		}
		catch(DataIntegrityViolationException x) {
			throw new com.hyshner.services.exeptions.DataIntegrityViolationException("Não é possivel excluir, há pedidos existentes");
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
		return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null,null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()),pe.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2()!=null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj,Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
