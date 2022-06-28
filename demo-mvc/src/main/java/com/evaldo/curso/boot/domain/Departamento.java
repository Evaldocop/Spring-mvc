package com.evaldo.curso.boot.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {

	
	@NotBlank(message = "Nome obrigatório")
	@Size(min = 3,max = 60, message = "O nome do Departamento de ter entre {min} e {max} caracteres")
	@Column(name = "nome", nullable = false,unique = true, length = 60)
	private String nome;
	
	
	//necessario para mapeamento bidirecional
	@OneToMany(mappedBy = "departamento")
	private List<Cargo>cargos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

}
