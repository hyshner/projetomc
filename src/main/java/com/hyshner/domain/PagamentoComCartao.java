package com.hyshner.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.hyshner.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;
		
		
	public PagamentoComCartao() {
	}


	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer nDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = nDeParcelas;
		
	}


	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}


	public void setNumeroDeParcelas(Integer nDeParcelas) {
		this.numeroDeParcelas = nDeParcelas;
	}
	
	
	
	
}


