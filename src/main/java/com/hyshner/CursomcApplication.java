package com.hyshner;


import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hyshner.domain.Categoria;
import com.hyshner.domain.Produto;
import com.hyshner.repositories.CategoriaRepositorie;
import com.hyshner.repositories.ProdutoRepositorie;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepositorie catRepo;
	@Autowired
	private ProdutoRepositorie produtoRepositorie;
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
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
	catRepo.save(Arrays.asList(cat1,cat2));
	produtoRepositorie.save(Arrays.asList(p1,p2,p3));
	
		
	}

}

