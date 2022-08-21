package com.example.demo.domain.repository.restaurante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRepository;
import com.example.demo.domain.spec.RestauranteSpecs;

@Repository
public class RestauranteRepositoryImpl implements RestaurtanteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy //Lazy: avitar erro "circular references", 
	//pois estou importando im repository na impl dele mesmo.
	//"só vem quando pede"
	private RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> find (String nome,
	BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		
	// criar elementos para fazer consultas
	CriteriaBuilder builder = manager.getCriteriaBuilder();
		
    //CriteriaQuery composição
	CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
	Root<Restaurante> root = criteria.from(Restaurante.class); //from restaurante
	// from
	
	var predicates = new ArrayList<Predicate>();
	
	// se existir texto dentro do var nome.
	if (StringUtils.hasText(nome)) {
		//like
		predicates.add(builder
				.like(root.get("nome"), "%" + nome + "%"));
	}
	
	if(taxaFreteInicial != null) {
		//maior ou igual
		predicates.add(builder
				.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
	}
	
	if(taxaFreteFinal != null) {
		//maior ou igual
		predicates.add(builder
		.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		
	}
	
	//clausula responsavel de passar todos os predicatos
	criteria.where(predicates.toArray(new Predicate[0]));
	
    TypedQuery<Restaurante> query = manager.createQuery(criteria);
    return query.getResultList(); // retronar lista de restaurante
   
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis()
				.and(RestauranteSpecs.comNomeSemelhante(nome)));
	}
}
