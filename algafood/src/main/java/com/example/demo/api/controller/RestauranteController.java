package com.example.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRepository;
import com.example.demo.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	// Deu certo apenas dando run do AlgaFoodApplication
	@GetMapping (produces = MediaType.APPLICATION_JSON_VALUE) // Retorno apenas em json
	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}
	
	@GetMapping ("/{restauranteId}")
	public  ResponseEntity<Restaurante>  buscar( @PathVariable("restauranteId") Long restauranteId) {
		Restaurante restaurante = restauranteRepository.buscar(restauranteId);
		
		if(restauranteId != null ) {
			return ResponseEntity.ok(restaurante);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	

}
