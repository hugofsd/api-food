package com.example.demo.domain.repository.NoRepositoryBean;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

   public class CustomJpaRepositoryImlp<T, ID> extends SimpleJpaRepository<T, ID>
   implements CustomJpaRepository<T, ID> {

	  private EntityManager manager;
	   
	public CustomJpaRepositoryImlp(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		// TODO Auto-generated constructor stub
		
		this.manager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro() {
		// TODO Auto-generated method stub
		
       var jpql = "from " + getDomainClass().getName();
		
		T entity = manager.createQuery(jpql, getDomainClass())
			.setMaxResults(1) // limitar resultado em uma linha
			.getSingleResult();
		
		return Optional.ofNullable(entity);
	} 
	

}
