package com.lojaprodutos.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductDTO {

	private String name;
	
	private String description;
	
	private BigDecimal price;
	
}
