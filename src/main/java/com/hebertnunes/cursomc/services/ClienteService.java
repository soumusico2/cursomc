package com.hebertnunes.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hebertnunes.cursomc.domain.Cliente;
import com.hebertnunes.cursomc.repositories.ClienteRepository;
import com.hebertnunes.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		
		Optional <Cliente> cliente = clienteRepository.findById(id);
		
		return cliente.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ ", Tipo: " +Cliente.class.getName()));
	}
}
