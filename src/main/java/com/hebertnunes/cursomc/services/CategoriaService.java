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
	
	public Categoria find(Integer id) throws ObjectNotFoundException {
		
		Optional <Categoria> categoria = categoriaRepository.findById(id);
		
		return categoria.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ ", Tipo: " +Categoria.class.getName()));
	}
	
	
	public Categoria insert(Categoria obj) {
		
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		
		find(obj.getId());
		return categoriaRepository.save(obj);
	}
}
