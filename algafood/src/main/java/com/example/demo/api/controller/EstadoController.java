package com.example.demo.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.repository.EstadoRepository;
import com.example.demo.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping ("/{estadoId}")
	public ResponseEntity<Estado> buscar( @PathVariable("estadoId") Long estadoId ) {
		Optional <Estado>  estado = estadoRepository.findById(estadoId);
		
		if(estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//ResponseEntity usado pois o obj será tratado
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId,
			@RequestBody Estado estado){
		
		//buscar a estado
		Optional <Estado> estadoAtual = estadoRepository.findById(estadoId);
		
		if(estadoAtual != null) {
			//copiar o obj recebido e passar para o obj atual
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			 // em aspas são declaradas as propriedades ignoradas
			
			//salvar estado atual
			Estado estadoSalva = cadastroEstadoService.salvar(estadoAtual.get());
			
			//status ok
			return ResponseEntity.ok(estadoSalva);
		
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<Estado> remover ( @PathVariable Long estadoId){
		try { 
			cadastroEstadoService.excluir(estadoId);
			return ResponseEntity.noContent().build();
		} catch(EntidadeNaoEncontradaException erro){
			return ResponseEntity.notFound().build();	
		} catch(EntidadeEmUsoException erro){
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
			
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar( @RequestBody Estado estado) {
		return cadastroEstadoService.salvar(estado);
	}
	
}
