package com.hebertnunes.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hebertnunes.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
