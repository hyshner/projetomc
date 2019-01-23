package com.hyshner.domain;

import javax.persistence.Entity;

import com.hyshner.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Integer nDeParcelas;
		
		
	public PagamentoComCartao() {
	}


	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer nDeParcelas) {
		super(id, estado, pedido);
		this.nDeParcelas = nDeParcelas;
		
	}


	public Integer getnDeParcelas() {
		return nDeParcelas;
	}


	public void setnDeParcelas(Integer nDeParcelas) {
		this.nDeParcelas = nDeParcelas;
	}
	
	
	
	
}


