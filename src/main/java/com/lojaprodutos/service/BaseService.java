package com.lojaprodutos.service;

import java.util.Map;
import java.util.Optional;

import com.lojaprodutos.exception.AppRequestException;

public abstract class BaseService {

	protected void validRequiredFields(Map<String, Optional<?>> map) {
		Object[] camposNaoPreenchidos = map
				.entrySet()
				.stream()
				.filter(e -> e.getValue().isEmpty())
				.map(Map.Entry::getKey).toArray();
		if(camposNaoPreenchidos.length > 0 ) {
			throw new AppRequestException("Campos obrigatórios não preenchidos!");
		}
	}
	
}
