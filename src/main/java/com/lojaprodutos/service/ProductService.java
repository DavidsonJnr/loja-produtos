package com.lojaprodutos.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojaprodutos.model.Product;
import com.lojaprodutos.model.dto.ProductDTO;
import com.lojaprodutos.repository.product.ProductReadRepository;
import com.lojaprodutos.repository.product.ProductWriteRepository;

@Service
public class ProductService {

	@Autowired
	private ProductReadRepository productReadRepository;

	@Autowired
	private ProductWriteRepository productWriteRepository;

	public List<Product> getAllProducts() {
		return productReadRepository.findAll();
	}

	public Product getProductById(Long id) {
		return productReadRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));
	}

	public Product save(ProductDTO productDTO) {
		return productWriteRepository.save(this.buildProduct(productDTO));
	}

	private Product buildProduct(ProductDTO productDTO) {
		return Product.builder().name(productDTO.getName()).description(productDTO.getDescription())
				.price(productDTO.getPrice()).build();
	}

	public void delete(Long id) {
		productWriteRepository.deleteById(id);
	}

	public Product edit(Long id, Product product) {
		Product productBD = this.getProductById(id);
		BeanUtils.copyProperties(product, productBD, "id");
		return productWriteRepository.save(productBD);
	}

	public List<Product> search(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		return productReadRepository.search(q, minPrice, maxPrice);
	}

}
