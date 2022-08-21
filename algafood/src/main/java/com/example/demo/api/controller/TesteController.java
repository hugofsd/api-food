package com.example.demo.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.CozinhaRepository;
import com.example.demo.domain.repository.RestauranteRepository;
import com.example.demo.domain.spec.RestauranteSpecs;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/cozinhas/nomes")
	public List<Cozinha> cozinhaPorNome(@RequestParam("nome") String nome){
		return cozinhaRepository.findByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/existe")
	public boolean cozinhaExiste(String nome){
		return cozinhaRepository.existsByNome(nome);
	}
	
	@GetMapping("/cozinhas/nomeUnico")
	public Optional<Cozinha> findByNomeUnico(String nome){
		return cozinhaRepository.findUnicoByNome(nome);
	}
	
	@GetMapping("/restaurante/por-taxa-frete")
	public List<Restaurante> Frete(BigDecimal taxaInicial,
	BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal );
	}
	
	@GetMapping("/restaurante/por-nome")
	public List<Restaurante> findByNomeContainingAndCozinhaId (String nome, Long cozinhaId){
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
	}
	
	@GetMapping("/restaurante/primeiro-nome-restaurante")
	Optional <Restaurante> FiltrarPrimeiro(String nome){
		return restauranteRepository.findFirstByNomeContaining(nome);
	}
	
	@GetMapping("/restaurante/top-2-restaurantes")
	public List<Restaurante> FiltrarTopDois(String nome){
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/restaurante/count-cozinha")
	public int quantidadeRestaurantes(Long cozinhaId){
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
	
	@GetMapping("/restaurante/por-nome-two")
	public List<Restaurante> restaurantesPorNome(String nome, BigDecimal taxaFreteInical,
			BigDecimal taxaFreteFinal ){
		return restauranteRepository.find(nome, taxaFreteInical, taxaFreteFinal);
	}
	
	@GetMapping("/restaurante/com-frete-spec")
	public List<Restaurante> restaurantesComFreteGratis(String nome){
		
		//findComFreteGratis: vem do RestaurtanteRepositoryQuery, implementado no impl
		return restauranteRepository.findComFreteGratis(nome);
	
	}
	

}

