package com.hebertnunes.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hebertnunes.cursomc.domain.Categoria;
import com.hebertnunes.cursomc.repositories.CategoriaRepository;
import com.hebertnunes.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		
		Optional <Categoria> categoria = categoriaRepository.findById(id);
		
		return categoria.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ ", Tipo: " +Categoria.class.getName()));
	}
}
