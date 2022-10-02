package com.example.demo.api.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

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
		return cozinhaRepository.findAll();
	}
	
	@GetMapping ("/{cozinhaId}")
	public  ResponseEntity<Cozinha>  buscar( @PathVariable("cozinhaId") Long cozinhaId) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
		
		//se cozinha está presente
		if(cozinha.isPresent() ) {
			return ResponseEntity.ok(cozinha.get());
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
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
	@RequestBody Cozinha cozinha) {
		
		//buscar a cozinha
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);
		
		if(cozinhaAtual.isPresent()) {
			//copiar o obj recebido e passar para o obj atual
		    BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id"); 
		    // em aspas são declaradas as propriedades ignoradas
			
		    //salvar cozinha atual
		    Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
		    
		    //status ok
		    return ResponseEntity.ok(cozinhaSalva);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
//	@DeleteMapping("/{cozinhaId}")
//	public ResponseEntity<Cozinha> remover( @PathVariable Long cozinhaId){
//		try {
//		cadastroCozinhaService.excluir(cozinhaId);
//    	return ResponseEntity.noContent().build();	
//		}
//		
////		catch(EntidadeNaoEncontradaException erro){
////			return ResponseEntity.notFound().build();	
////		}
//		
//		catch(EntidadeEmUsoException erro){
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
//		
//	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover( @PathVariable Long cozinhaId){
			cadastroCozinhaService.excluir(cozinhaId);
	}
	
	 
}
