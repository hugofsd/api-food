package com.example.demo.domain.repository.restaurante;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl implements RestaurtanteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find (String nome,
	BigDecimal taxaFreteInicial, BigDecimal taxaFreteFianl){
		
		//consulta de restaurante por nome, usando like para pegar qualquer letra
		// e mapeia de taxafrete consultando valores entre inicial e final com between
		var jpql ="from Restaurante where nome like :nome"
				+ " and taxaFrete between :taxaInicial and :taxaFinal";
		
		return manager.createQuery(jpql, Restaurante.class)
				.setParameter("nome", "%" + nome + "%")
				.setParameter("taxaInicial", taxaFreteInicial)
				.setParameter("taxaFinal", taxaFreteFianl)
				.getResultList();
	}
}
