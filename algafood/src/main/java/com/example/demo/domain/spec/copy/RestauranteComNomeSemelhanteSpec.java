package com.example.demo.domain.spec.copy;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.domain.model.Restaurante;

import lombok.AllArgsConstructor;

//para receber o "nome" e atribuir a var de instancia
@AllArgsConstructor
public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante> {

	
	private static final long serialVersionUID = 1L;
	
	//recebe do impl
	private String nome;

	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) {
		// TODO Auto-generated method stub
		

		return builder.like(root.get("nome"), "%" + nome + "%");
	}
	

}
