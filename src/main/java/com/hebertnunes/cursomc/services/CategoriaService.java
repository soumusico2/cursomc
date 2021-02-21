package com.hebertnunes.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.hebertnunes.cursomc.domain.Categoria;
import com.hebertnunes.cursomc.repositories.CategoriaRepository;
import com.hebertnunes.cursomc.services.exceptions.DataIntegrityException;
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
	
	public void delete(Integer id) {
		
		find(id);
		
		try {
			categoriaRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produto");
		}
		
	}
	
	public List<Categoria> findAll() {
		
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPorPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPorPage, Direction.valueOf(direction), orderBy);
		
		return categoriaRepository.findAll(pageRequest);
	}
}
