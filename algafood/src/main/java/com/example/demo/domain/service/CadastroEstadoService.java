package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.EstadoNaoEncontradaException;
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String  MSG_ESTADO_EM_USO =
			"Estado de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Transactional 
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	@Transactional 
	public void excluir (Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch(EmptyResultDataAccessException erro) {
			//throw é um statement q manda a exceção ser lançada
			throw new EstadoNaoEncontradaException(estadoId);
			
		} catch(DataIntegrityViolationException erro) {
			throw new EstadoNaoEncontradaException(estadoId);
		}
	}
	
	@Transactional 
	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
	    .orElseThrow(() -> new
	    		EstadoNaoEncontradaException(estadoId));
	    		
	}
	
	

}
