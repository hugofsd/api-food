package com.example.demo.domain.repository.restaurante;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		
		//consulta de restaurante por nome, usando like para pegar qualquer letra
		// e mapeia de taxafrete consultando valores entre inicial e final com between
		var jpql = new StringBuilder();
		
       jpql.append("from Restaurante where 0 = 0 ");
		
         var parametros = new HashMap<String, Object>();
		
		if (StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%"); // mapeia var
		}
		
		if (taxaFreteInicial != null) {
			jpql.append("and taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaFreteInicial);
		}
		
		if (taxaFreteFinal != null) {
			jpql.append("and taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFreteFinal);
		}
		
		TypedQuery<Restaurante> query = manager
				.createQuery(jpql.toString(), Restaurante.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();
	}
}
