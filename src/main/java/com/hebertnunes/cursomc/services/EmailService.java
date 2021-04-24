package com.hebertnunes.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.hebertnunes.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
