package com.hebertnunes.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hebertnunes.cursomc.domain.Categoria;
import com.hebertnunes.cursomc.domain.Cidade;
import com.hebertnunes.cursomc.domain.Cliente;
import com.hebertnunes.cursomc.domain.Endereco;
import com.hebertnunes.cursomc.domain.Estado;
import com.hebertnunes.cursomc.domain.ItemPedido;
import com.hebertnunes.cursomc.domain.Pagamento;
import com.hebertnunes.cursomc.domain.PagamentoComBoleto;
import com.hebertnunes.cursomc.domain.PagamentoComCartao;
import com.hebertnunes.cursomc.domain.Pedido;
import com.hebertnunes.cursomc.domain.Produto;
import com.hebertnunes.cursomc.domain.enums.EstadoPagamento;
import com.hebertnunes.cursomc.domain.enums.TipoCliente;
import com.hebertnunes.cursomc.repositories.CategoriaRepository;
import com.hebertnunes.cursomc.repositories.CidadeRepository;
import com.hebertnunes.cursomc.repositories.ClienteRepository;
import com.hebertnunes.cursomc.repositories.EnderecoRepository;
import com.hebertnunes.cursomc.repositories.EstadoRepository;
import com.hebertnunes.cursomc.repositories.ItemPedidoRepository;
import com.hebertnunes.cursomc.repositories.PagamentoRepository;
import com.hebertnunes.cursomc.repositories.PedidoRepository;
import com.hebertnunes.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
