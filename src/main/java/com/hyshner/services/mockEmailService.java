package com.hyshner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class mockEmailService extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(mockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de Email");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
		
	}

}
