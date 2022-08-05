package com.example.demo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext // engetar o manager
	private EntityManager manager; //para salvar e fazer consultas.
	
	
	public List<Cozinha> listar(){
		return manager.createQuery("from Cozinha", Cozinha.class)
				.getResultList();
	
	}
	
	@Transactional
    public Cozinha adicionar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	//buscar por id
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
}
