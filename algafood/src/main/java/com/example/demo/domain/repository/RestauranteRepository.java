package com.example.demo.domain.repository;

import java.util.List;

public interface RestauranteRepository<Restaurante> {

	List<Restaurante> listar();
	
	Restaurante buscar(Long id);
	
	Restaurante salvar(Restaurante restaurante);
	
	void remover(Restaurante restaurante);
	
}
