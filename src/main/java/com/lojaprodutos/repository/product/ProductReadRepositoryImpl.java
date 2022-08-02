package com.lojaprodutos.repository.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.lojaprodutos.model.Product;

public class ProductReadRepositoryImpl implements ProductReadRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Product> search(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> root = criteriaQuery.from(Product.class);
		Predicate[] predicates = this.applyFilters(q, minPrice, maxPrice, criteriaBuilder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	private Predicate[] applyFilters(String q, BigDecimal minPrice, BigDecimal maxPrice, CriteriaBuilder builder, Root<Product> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (StringUtils.isNotBlank(q)) {
			predicates.add(builder.or(builder.like(builder.lower(root.get("name")), "%" + q + "%"),
					builder.like(builder.lower(root.get("description")), "%" + q + "%")));
		}
		
		if (minPrice != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("price"), minPrice));
		}
		
		if (maxPrice != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("price"), maxPrice));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}
