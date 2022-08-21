package com.example.demo.domain.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.spec.restaurante.RestauranteComNomeSemelhanteSpec;

public class RestauranteSpecs {

	
	public static Specification<Restaurante> comFreteGratis() {
		return (root, query, builder) -> 
		builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		return (root, query, builder) -> 
		builder.like(root.get("nome"), "%" + nome + "%");
	}
	
}
