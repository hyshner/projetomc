package com.hyshner;


import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hyshner.domain.Categoria;
import com.hyshner.domain.Cidade;
import com.hyshner.domain.Cliente;
import com.hyshner.domain.Endereco;
import com.hyshner.domain.Estado;
import com.hyshner.domain.Produto;
import com.hyshner.domain.enums.TipoCliente;
import com.hyshner.repositories.CategoriaRepositorie;
import com.hyshner.repositories.CidadeRepositorie;
import com.hyshner.repositories.ClienteRepositorie;
import com.hyshner.repositories.EnderecoRepositorie;
import com.hyshner.repositories.EstadoRepositorie;
import com.hyshner.repositories.ProdutoRepositorie;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepositorie catRepo;
	@Autowired
	private ProdutoRepositorie produtoRepositorie;
	@Autowired
	private EstadoRepositorie estadorepositorie;
	@Autowired
	private CidadeRepositorie cidaderepositorie;
	@Autowired
	private ClienteRepositorie clienteRepositorie;
	@Autowired
	private EnderecoRepositorie enderecoRepositorie;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	
	}
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria (null,"Escritório");
		
		Produto p1=new Produto(null, "computador", 2000.00);
		Produto p2=new Produto(null, "impressora", 800.00);
		Produto p3=new Produto(null, "mouse", 80.00);
		
		Estado est1 =new Estado(null, "Minas Gerais");
		Estado est2 =new Estado(null, "São Paulo");
		
		Cidade c1 =new Cidade(null, "Uberlândia",est1);
		Cidade c2 =new Cidade(null, "São Paulo",est2);
		Cidade c3 =new Cidade(null, "Campinas",est2);
		
		Cliente cli1 =new Cliente(null, "Maria Silva" ,"maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 =new Endereco(null, "Rua Flores", "300", "apt 303", "Jardim", "3822834",cli1,c1);
		Endereco e2 =new Endereco(null, "Avenida Matos", "105", "sala 800", "centro", "38777012", cli1, c2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
	catRepo.save(Arrays.asList(cat1,cat2));
	produtoRepositorie.save(Arrays.asList(p1,p2,p3));
	estadorepositorie.save(Arrays.asList(est1,est2));
	cidaderepositorie.save(Arrays.asList(c1,c2,c3));
	clienteRepositorie.save(Arrays.asList(cli1));
	enderecoRepositorie.save(Arrays.asList(e1,e2));
	
		
	}

}

