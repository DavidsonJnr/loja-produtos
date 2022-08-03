package com.lojaprodutos.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity {
	
	@Column(nullable = false, name = "created_at")
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Version
	@Column(nullable = false, name = "version", columnDefinition = "integer default 0")
	private Integer versao;
}
