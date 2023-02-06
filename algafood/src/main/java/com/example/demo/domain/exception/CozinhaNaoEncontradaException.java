package com.example.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(HttpStatus.NOT_FOUND) // só está aqui para ficar explicito pq já é erdado
public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	//especificar a mensagem
	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem); 
	}
	
	//passando o status ID
	public CozinhaNaoEncontradaException(Long cozinhaID) {
		this(String.format("Não existe um cadastro de cozinha com o codigo %d", cozinhaID));
	}

}
