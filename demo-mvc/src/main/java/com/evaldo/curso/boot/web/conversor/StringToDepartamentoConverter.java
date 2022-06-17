package com.evaldo.curso.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.evaldo.curso.boot.domain.Departamento;
import com.evaldo.curso.boot.service.DepartamentoService;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

	@Autowired
	private DepartamentoService departamentoService;
	
	
	@Override
	public Departamento convert(String value) {
		if(value.isEmpty()) {
			return null;
		}
		
		Long id = Long.valueOf(value);
		return departamentoService.buscarPorId(id);
	}

}
