package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}
	
	public void excluir (Long cozinhaId) {
		try {
			cozinhaRepository.remover(cozinhaId);
		} catch(EmptyResultDataAccessException erro) {
			throw new EntidadeNaoEncontradaException(
			String.format("Não existe um cadastro de cozinha com o codigo %d", cozinhaId));	
		}
		 catch(DataIntegrityViolationException erro){
			throw new EntidadeEmUsoException(
			String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
		}
		
	}

}
