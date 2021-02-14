package com.hebertnunes.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hebertnunes.cursomc.domain.Categoria;
import com.hebertnunes.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(int id) {
		
		Optional <Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElse(null);
	}
}
