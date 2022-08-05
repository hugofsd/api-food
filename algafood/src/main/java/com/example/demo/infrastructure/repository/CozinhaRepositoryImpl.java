package com.example.demo.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository<Cozinha>{

	@Override
	public List<Cozinha> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cozinha buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cozinha salvar(Cozinha cozinha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Cozinha cozinha) {
		// TODO Auto-generated method stub
		
	}

}
