package com.example.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(HttpStatus.NOT_FOUND) // só está aqui para ficar explicito pq já é erdado
public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	//especificar a mensagem
	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem); 
	}
	
	//passando o status ID
	public CidadeNaoEncontradaException(Long estadoId) {
		this(String.format("Não existe um cadastro de cidade com o codigo %d", estadoId));
	}

}
