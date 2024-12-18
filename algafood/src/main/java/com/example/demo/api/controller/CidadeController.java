package com.example.demo.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.exceptionhandler.Problem;
import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.EstadoNaoEncontradaException;
import com.example.demo.domain.exception.NegocioException;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.model.Estado;
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
		return cidadeRepository.findAll();
	}
	
	@GetMapping ("/{cidadeId}")
	public Cidade buscar(@PathVariable("cidadeId") Long cidadeId){
		return cadastroCidadeService.buscarOuFalhar(cidadeId);
	}
	
	@PutMapping ("/{cidadeId}")
	public Cidade atualizar(@PathVariable("cidadeId") Long cidadeId,
			@RequestBody @Valid Cidade cidade){
	    try {
	    	//buscar cidade
			Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
			
			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			return cadastroCidadeService.salvar(cidadeAtual);
	    } catch (EntidadeNaoEncontradaException e){
	     throw new NegocioException(e.getMessage(), e); //se o estado não existir, "e" causa
	     }
		
        }
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover (@PathVariable Long cidadeId){
	   cadastroCidadeService.excluir(cidadeId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar (@RequestBody @Valid Cidade cidade) {
		  try {
				return cadastroCidadeService.salvar(cidade);
		    } catch (EstadoNaoEncontradaException e){
		    throw new NegocioException(e.getMessage(), e); //se o estado não existir
		    }
	}
	

}
	
	
	
