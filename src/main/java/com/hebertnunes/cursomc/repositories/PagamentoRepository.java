package com.hebertnunes.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hebertnunes.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
