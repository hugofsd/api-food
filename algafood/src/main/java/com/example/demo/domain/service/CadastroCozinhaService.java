package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	
	@Transactional 
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	@Transactional 
	public void excluir (Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
		
	}
	
	public Cozinha buscarOuFalhar(Long cozinhaId) {
	    return cozinhaRepository.findById(cozinhaId)
	        .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}       

}
