package com.hyshner.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.hyshner.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instanteDoPeedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPeedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(cal.getTime());
	}

}
