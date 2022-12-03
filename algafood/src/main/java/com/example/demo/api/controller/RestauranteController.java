package com.example.demo.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
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

import com.example.demo.domain.exception.CozinhaNaoEncontradaException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.NegocioException;
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
		return restauranteRepository.findAll();
	}
	
	@GetMapping ("/{restauranteId}")
	public  Restaurante buscar( @PathVariable("restauranteId") Long restauranteId) {
	
		return cadastroRestauranteService.buscarOuFalhar(restauranteId);
	}
	
	//ResponseEntity usado para customizar o status com o tipo do corpo <> Restaurante
	@PostMapping
	public Restaurante adicionar( @RequestBody Restaurante restaurante){
		try {
			return cadastroRestauranteService.salvar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
			
		}
		
	}
	
	@PutMapping("/{restauranteId}")
	public Restaurante atualizar (@PathVariable Long restauranteId, 
			@RequestBody Restaurante restaurante){
		
		 Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		    
		 BeanUtils.copyProperties(restaurante, restauranteAtual, 
		  "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
		  
		 try {
			 return cadastroRestauranteService.salvar(restauranteAtual);
		 }catch (CozinhaNaoEncontradaException  e) {
				throw new NegocioException(e.getMessage());
		  }
		 
		
	}

}
