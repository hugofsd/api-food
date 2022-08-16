package com.example.demo.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRepository;

// pacote infrastructure para fazer regras que não são de negocio. Ex: envio de e-mail
// Classe para implementar de como fazer o acesso ao banco, 
// impl implementação
@Component
public class CozinhaRepositoryImpl implements CozinhaRepository{

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
	public void remover(Long id) {
		Cozinha cozinha = buscar(id);
		
		if(cozinha == null) {
			//EmptyResultDataAccessException: id n existe
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cozinha);	
	}

  // METODOS A+ FORA DO CURSO

}
