package com.lojaprodutos.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojaprodutos.model.Product;

@Repository
public interface ProductWriteRepository extends JpaRepository<Product, Long>{

}
