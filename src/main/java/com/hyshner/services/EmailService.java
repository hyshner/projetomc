package com.hyshner.services;

import org.springframework.mail.SimpleMailMessage;

import com.hyshner.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj) ;
	
	void sendEmail(SimpleMailMessage msg) ;

}
