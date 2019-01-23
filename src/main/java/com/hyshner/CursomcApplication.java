package com.hyshner;


import java.text.SimpleDateFormat;
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
import com.hyshner.domain.Pagamento;
import com.hyshner.domain.PagamentoComBoleto;
import com.hyshner.domain.PagamentoComCartao;
import com.hyshner.domain.Pedido;
import com.hyshner.domain.Produto;
import com.hyshner.domain.enums.EstadoPagamento;
import com.hyshner.domain.enums.TipoCliente;
import com.hyshner.repositories.CategoriaRepository;
import com.hyshner.repositories.CidadeRepository;
import com.hyshner.repositories.ClienteRepository;
import com.hyshner.repositories.EnderecoRepository;
import com.hyshner.repositories.EstadoRepository;
import com.hyshner.repositories.PagamentoRepository;
import com.hyshner.repositories.PedidoRepository;
import com.hyshner.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 =new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1 , e1);
		Pedido ped2 =new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pgto1 =new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 =new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
	categoriaRepository.save(Arrays.asList(cat1, cat2));
	produtoRepository.save(Arrays.asList(p1, p2, p3));
	estadoRepository.save(Arrays.asList(est1, est2));
	cidadeRepository.save(Arrays.asList(c1, c2, c3));
	clienteRepository.save(Arrays.asList(cli1));
	enderecoRepository.save(Arrays.asList(e1, e2));
	pedidoRepository.save(Arrays.asList(ped1, ped2));
	pagamentoRepository.save(Arrays.asList(pgto1, pgto2));
	
		
	}

}

