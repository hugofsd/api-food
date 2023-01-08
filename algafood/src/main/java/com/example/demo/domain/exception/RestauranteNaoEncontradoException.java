package com.example.demo.domain.exception;


//@ResponseStatus(HttpStatus.NOT_FOUND) // só está aqui para ficar explicito pq já é erdado
public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	//especificar a mensagem
	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem); 
	}
	
	//passando o status ID
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
	}

}
