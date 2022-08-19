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
	public List<Restaurante> NomeCozinha(String nome, Long cozinhaId){
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
	}
}

