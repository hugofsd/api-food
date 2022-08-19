package com.example.demo.domain.repository.restaurante;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.demo.domain.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl implements RestaurtanteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find (String nome,
	BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		
	// criar elementos para fazer consultas
	CriteriaBuilder builder = manager.getCriteriaBuilder();
		
    //CriteriaQuery composição
	CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
	criteria.from(Restaurante.class); //from restaurante
		
    TypedQuery<Restaurante> query = manager.createQuery(criteria);
    return query.getResultList(); // retronar lista de restaurante
   
	}
}
