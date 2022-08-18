package com.example.demo.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRepository;
import com.example.demo.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
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
	
	//ResponseEntity usado para customizar o status com o tipo do corpo <> Restaurante
	@PostMapping
	public ResponseEntity<?> adicionar( @RequestBody Restaurante restaurante){
		try {
			restaurante = cadastroRestauranteService.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurante);
		}catch (EntidadeNaoEncontradaException erro) {
			//badRequest para notificar que alguma coisa incluida foi errada
			return ResponseEntity.badRequest()
					.body(erro.getMessage());
		}
	
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar (@PathVariable Long restauranteId, 
			@RequestBody Restaurante restaurante){
		
		try {
			//buscar o restaurante
			Restaurante restauranteAutual = restauranteRepository
			.buscar(restauranteId);
			
			if(restauranteAutual != null) {
				//copiar o obj recebido e passar para o obj atual
				BeanUtils.copyProperties(restaurante, restauranteAutual, "id");
	            // em aspas são declaradas as propriedades ignoradas
				
			    //salvar cozinha atual
				restauranteAutual = cadastroRestauranteService.salvar(restauranteAutual);
				 //status ok
			    return ResponseEntity.ok(restauranteAutual);
			} else {
				return ResponseEntity.notFound().build();
			}
			
		} catch (EntidadeNaoEncontradaException erro) {
			//badRequest para notificar que alguma coisa incluida foi errada
			return ResponseEntity.badRequest()
					.body(erro.getMessage());
		}
		
		
	}
	
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
				@RequestBody Map<String, Object> campos){
		
		//buscar o restaurante
		Restaurante restauranteAtual = restauranteRepository
		.buscar(restauranteId);
		
		if(restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}
		
		
		merge(campos);
			
			
		return atualizar(restauranteId, restauranteAtual);
	}

	private void merge(Map<String, Object> campos) {
		campos.forEach((nomePropriedade, valorPropriedade) -> {
		
			System.out.println(nomePropriedade + " = " + valorPropriedade);
				
		});
	}
	

}
