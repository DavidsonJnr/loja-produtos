package com.lojaprodutos.repository.product;

import java.math.BigDecimal;
import java.util.List;

import com.lojaprodutos.model.Product;

public interface ProductReadRepositoryCustom {

	List<Product> search(String q, BigDecimal minPrice, BigDecimal maxPrice);
	
}
