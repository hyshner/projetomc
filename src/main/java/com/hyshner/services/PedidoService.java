package com.hyshner.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyshner.domain.ItemPedido;
import com.hyshner.domain.PagamentoComBoleto;
import com.hyshner.domain.Pedido;
import com.hyshner.domain.enums.EstadoPagamento;
import com.hyshner.repositories.ClienteRepository;
import com.hyshner.repositories.ItemPedidoRepository;
import com.hyshner.repositories.PagamentoRepository;
import com.hyshner.repositories.PedidoRepository;
import com.hyshner.repositories.ProdutoRepository;
import com.hyshner.services.exeptions.ObjectNotFoudException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoR;
	@Autowired
	private ProdutoService produtoS;
	@Autowired
	private ItemPedidoRepository itemPedidoR;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoudException("Objeto não encontrado!: id"+ id + "-- tipo: " + Pedido.class.getName() );
		}
		return obj;
				
		
	}
	@Transactional(readOnly=false)
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto)obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto,obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoR.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
			ip.setPreco(produtoS.find(ip.getProduto().getId()).getPreco());
			
		}
		itemPedidoR.save(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
	
}
