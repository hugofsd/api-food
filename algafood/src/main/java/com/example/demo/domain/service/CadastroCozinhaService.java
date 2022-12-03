package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.exception.CozinhaNaoEncontradaException;
import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
    //	constante
	
	private static final String  MSG_COZINHA_NAO_ENCONTRADA =
			"Não existe um cadastro de cozinha com o codigo %d";
	private static final String  MSG_COZINHA_EM_USO =
			"Cozinha de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir (Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
		} catch(EmptyResultDataAccessException erro) {
			
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//			String.format("Não existe um cadastro de cozinha com o codigo %d", cozinhaId));		
			throw new EntidadeNaoEncontradaException(
			String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));	
		}
		 catch(DataIntegrityViolationException erro){
			  throw new CozinhaNaoEncontradaException(cozinhaId);
		}
		
	}
	
	public Cozinha buscarOuFalhar(Long cozinhaId) {
	    return cozinhaRepository.findById(cozinhaId)
	        .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}       

}
