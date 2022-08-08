package com.example.demo.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRepository;

// pacote infrastructure para fazer regras que não são de negocio. Ex: envio de e-mail
// Classe para implementar de como fazer o acesso ao banco, 
// impl implementação
@Component
public class CozinhaRepositoryImpl implements CozinhaRepository<Cozinha>{

	@PersistenceContext // engetar o manager
	private EntityManager manager; //para salvar e fazer consultas.
	
	@Override
	public List<Cozinha> listar(){
		return manager.createQuery("from Cozinha", Cozinha.class)
				.getResultList();
	
	}
	
	// metodo capas de salvar e editar
	@Transactional
	@Override
    public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	//buscar por id
	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);	
	}

}
