package com.hebertnunes.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hebertnunes.cursomc.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	
}
