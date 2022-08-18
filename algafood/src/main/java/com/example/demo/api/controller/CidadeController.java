package com.example.demo.api.controller;

import java.util.List;

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
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.repository.CidadeRepository;
import com.example.demo.domain.repository.EstadoRepository;
import com.example.demo.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@GetMapping
	public List<Cidade> listar(){
		return cidadeRepository.listar();
	}
	
	@GetMapping ("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable("cidadeId") Long cidadeId){
		Cidade cidade = cidadeRepository.buscar(cidadeId);
		
		if(cidade != null) {
			return ResponseEntity.ok(cidade);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping ("/{cidadeId}")
	public ResponseEntity<Cidade> atualizar(@PathVariable("cidadeId") Long cidadeId,
			@RequestBody Cidade cidade){
		
		//buscar cidade
		Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);
		
		if(cidadeAtual != null) {
			//copiar o obj recebido e passar para o obj atual
			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			// em aspas são  declaradas as propriedades ignoradas
			
			//salvar
			cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);
		
			//status ok
			return ResponseEntity.ok(cidadeAtual);
		} else {
			return ResponseEntity.notFound().build();
		    }
		}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover (@PathVariable Long cidadeId){
		
			try {
				cadastroCidadeService.excluir(cidadeId);
				return ResponseEntity.noContent().build();
			} catch(EntidadeNaoEncontradaException erro){
				return ResponseEntity.notFound().build();	
			} catch(EntidadeEmUsoException erro){
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar (@RequestBody Cidade cidade) {
		return cadastroCidadeService.salvar(cidade);
	}
	
}
	
	
	
