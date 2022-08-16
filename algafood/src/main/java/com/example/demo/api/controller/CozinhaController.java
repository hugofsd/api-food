package com.example.demo.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRepository;
import com.example.demo.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
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
			return ResponseEntity.notFound().build();
		}

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar( @RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}
	
	
	//ResponseEntity usado pois o obj será tratado
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		
		//buscar a cozinha
		Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinhaAtual != null) {
			//copiar o obj recebido e passar para o obj atual
		    BeanUtils.copyProperties(cozinha, cozinhaAtual, "id"); 
		    // em aspas são declaradas as propriedades ignoradas
			
		    //salvar cozinha atual
		    cozinhaRepository.salvar(cozinhaAtual);
		    
		    //status ok
		    return ResponseEntity.ok(cozinhaAtual);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover( @PathVariable Long cozinhaId){
		try {
		cadastroCozinhaService.excluir(cozinhaId);
    	return ResponseEntity.noContent().build();	
		}
		catch(EntidadeNaoEncontradaException erro){
			return ResponseEntity.notFound().build();	
		}
		
		catch(EntidadeEmUsoException erro){
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
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
