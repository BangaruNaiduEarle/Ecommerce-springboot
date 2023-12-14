package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductShareLinkModel;

@Repository
public interface ProductShareLinkRepo extends JpaRepository<ProductShareLinkModel, Long> {

	ProductShareLinkModel findByProductId(Long productId);

}
