package com.example.demo.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.RestauranteNaoEncontradoException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.CozinhaRepository;
import com.example.demo.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	//mensagens estaticas
	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO 
    = "Não existe um cadastro de restaurante com código %d";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastoCozinhaService;
	
	public Restaurante salvar (Restaurante restaurante) {
		
		//buscar id da cozinha atrelada ao restaurante
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cadastoCozinhaService.buscarOuFalhar(cozinhaId);
				
		//orElseThrow chamar opção de erro.
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}
	
}
