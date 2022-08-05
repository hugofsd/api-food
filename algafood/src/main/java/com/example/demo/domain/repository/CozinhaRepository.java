package com.example.demo.domain.repository;

import java.util.List;

public interface CozinhaRepository<Cozinha> {

	List<Cozinha> listar();
	
	Cozinha buscar(Long id);
	
	Cozinha salvar(Cozinha cozinha);
	
	void remover(Cozinha cozinha);
	
}
