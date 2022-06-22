package com.evaldo.curso.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.evaldo.curso.boot.domain.Cargo;
import com.evaldo.curso.boot.service.CargoService;

@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

	@Autowired
	private CargoService cargoService;
	
	
	@Override
	public Cargo convert(String value) {
		if(value.isEmpty()) {
			return null;
		}
		
		Long id = Long.valueOf(value);
		return cargoService.buscarPorId(id);
	}

}
