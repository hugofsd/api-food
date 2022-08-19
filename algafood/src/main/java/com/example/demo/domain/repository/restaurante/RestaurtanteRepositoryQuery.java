package com.example.demo.domain.repository.restaurante;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.domain.model.Restaurante;

// interface refatorada do impl 
public interface RestaurtanteRepositoryQuery {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFianl);

}