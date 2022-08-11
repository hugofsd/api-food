package com.example.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	// Deu certo apenas dando run do AlgaFoodApplication
	@GetMapping (produces = MediaType.APPLICATION_JSON_VALUE) // Retorno apenas em json
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}
	
	@GetMapping ("/{cozinhaId}")
	public  ResponseEntity<Cozinha>  buscar( @PathVariable("cozinhaId") Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinha != null ) {
			return ResponseEntity.ok(cozinha);
		} else {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			return ResponseEntity.notFound().build();
		}

	}
	
	//@ResponseStatus(HttpStatus.OK) // Escolher status
	@GetMapping ("controleStatus/{cozinhaId}")
	public ResponseEntity<Cozinha> buscarComControleStatus( @PathVariable("cozinhaId") Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		//exemplos de retornos com status
		return ResponseEntity.status(HttpStatus.OK).body(cozinha); 
		//return ResponseEntity.ok(cozinha);
	}
	
}
