package com.hebertnunes.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hebertnunes.cursomc.domain.ItemPedido;
import com.hebertnunes.cursomc.domain.PagamentoComBoleto;
import com.hebertnunes.cursomc.domain.Pedido;
import com.hebertnunes.cursomc.domain.enums.EstadoPagamento;
import com.hebertnunes.cursomc.repositories.ItemPedidoRepository;
import com.hebertnunes.cursomc.repositories.PagamentoRepository;
import com.hebertnunes.cursomc.repositories.PedidoRepository;
import com.hebertnunes.cursomc.repositories.ProdutoRepository;
import com.hebertnunes.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		
		Optional <Pedido> pedido = pedidoRepository.findById(id);
		
		return pedido.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ ", Tipo: " +Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto)obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) { 
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}
