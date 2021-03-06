package com.hebertnunes.cursomc.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hebertnunes.cursomc.domain.Categoria;
import com.hebertnunes.cursomc.dto.CategoriaDTO;
import com.hebertnunes.cursomc.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Categoria categoria = categoriaService.find(id);
		
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		
		Categoria obj = categoriaService.fromDTO(objDto);
		Categoria categoria = categoriaService.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		
		Categoria obj = categoriaService.fromDTO(objDto);
		
		obj.setId(id);
		obj = categoriaService.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	
	/**
	 * 
	 * @param id da Categroria
	 * @return response entity
	 * @throws ObjectNotFoundException
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		categoriaService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		
		List<Categoria> list = categoriaService.findAll();
		
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPorPage", defaultValue = "24") Integer linesPorPage, 
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction){
		
		Page<Categoria> list = categoriaService.findPage(page, linesPorPage, orderBy, direction);
		
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
}
