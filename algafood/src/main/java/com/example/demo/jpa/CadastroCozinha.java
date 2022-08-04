package com.example.demo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext // engetar o manager
	private EntityManager manager; //para salvar e fazer consultas.
	
	
	public List<Cozinha> listar(){
		return manager.createQuery("from Cozinha", Cozinha.class)
				.getResultList();
	
		
	}
}
