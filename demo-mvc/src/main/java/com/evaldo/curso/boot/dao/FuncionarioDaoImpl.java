package com.evaldo.curso.boot.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.evaldo.curso.boot.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {


	public List<Funcionario> findByNome(String nome) {
		return createQuery("select f from Funcionario f where f.nome like concat('%', ?1, '%')", nome);
	}

	public List<Funcionario> findByCargoId(Long id) {
		 return createQuery("select f from Funcionario f where f.cargo.id = ?1", id);
	}

	@Override
	public List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida) {
		 return createQuery("select f from Funcionario f where f.dataEntrada >= ?1 and f.dataSaida <= ?2",entrada, saida);
	}

	@Override
	public List<Funcionario> findByDataEntrada(LocalDate entrada) {
	 return createQuery("select f from Funcionario f where f.dataEntrada = ?1", entrada);
	}

	@Override
	public List<Funcionario> findByDataSaida(LocalDate saida) {
		return createQuery("select f from Funcionario f where f.dataSaida = ?1", saida);
	}

}
