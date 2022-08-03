package com.lojaprodutos.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojaprodutos.exception.AppRequestException;
import com.lojaprodutos.model.Product;
import com.lojaprodutos.model.dto.ProductDTO;
import com.lojaprodutos.repository.product.ProductReadRepository;
import com.lojaprodutos.repository.product.ProductWriteRepository;

@Service
public class ProductService extends BaseService {

	@Autowired
	private ProductReadRepository productReadRepository;

	@Autowired
	private ProductWriteRepository productWriteRepository;

	public List<Product> getAllProducts() {
		return productReadRepository.findAll();
	}

	public Product getProductById(Long id) {
		return productReadRepository.findById(id).orElseThrow();
	}

	public Product save(ProductDTO productDTO) {
		return productWriteRepository.save(this.buildProduct(productDTO));
	}

	private Product buildProduct(ProductDTO productDTO) {
		
		this.validRequiredFields(this.getRequiredFields(productDTO));
		if(productDTO.getPrice().intValue() <= 0)
			throw new AppRequestException("Price é negativo!");
			
		return Product.builder().name(productDTO.getName()).description(productDTO.getDescription())
				.price(productDTO.getPrice()).build();
	}

	public void delete(Long id) {
		productWriteRepository.deleteById(id);
	}

	public Product edit(Long id, ProductDTO productDTO) {
		Product productBD = this.getProductById(id);
		BeanUtils.copyProperties(this.buildProduct(productDTO), productBD, "id");
		return productWriteRepository.save(productBD);
	}

	public List<Product> search(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		return productReadRepository.search(q, minPrice, maxPrice);
	}
	
	private Map<String, Optional<?>> getRequiredFields(ProductDTO productDTO) {
		return new HashMap<>(Map.of(
				"name", Optional.ofNullable(productDTO.getName()),
				"description", Optional.ofNullable(productDTO.getDescription()),
				"price", Optional.ofNullable(productDTO.getPrice())
		));
	}

}
