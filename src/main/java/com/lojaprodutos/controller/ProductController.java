package com.lojaprodutos.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lojaprodutos.model.Product;
import com.lojaprodutos.model.dto.ProductDTO;
import com.lojaprodutos.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> cadastrar(@RequestBody ProductDTO productDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDTO));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllUsers() {
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> search(@RequestParam(required = false) String q, @RequestParam(required = false) BigDecimal minPrice, 
			@RequestParam(required = false) BigDecimal maxPrice) {
		return ResponseEntity.ok(productService.search(q, minPrice, maxPrice));
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productService.delete(id);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> edit(@PathVariable Long id, @RequestBody Product product) {
		return ResponseEntity.ok(productService.edit(id, product));
	}
	
}
