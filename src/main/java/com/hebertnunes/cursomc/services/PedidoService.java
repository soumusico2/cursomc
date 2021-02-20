package com.hebertnunes.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hebertnunes.cursomc.domain.Pedido;
import com.hebertnunes.cursomc.repositories.PedidoRepository;
import com.hebertnunes.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		
		Optional <Pedido> pedido = pedidoRepository.findById(id);
		
		return pedido.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ ", Tipo: " +Pedido.class.getName()));
	}
}
