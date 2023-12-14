package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductModel;

import jakarta.transaction.Transactional;
@Repository
public interface ProductRepo extends JpaRepository<ProductModel, Long> {

	List<ProductModel> findByCategoryId(Long categoryId);
	
    List<ProductModel> findByProductNameContaining(String productName);

	List<ProductModel> findByProductNameContainingAndCategoryId(String productName, Long categoryId);

	@Query("SELECT p FROM ProductModel p WHERE " +
	           "(:minPrice IS NULL OR p.productPrice >= :minPrice) AND " +
	           "(:maxPrice IS NULL OR p.productPrice <= :maxPrice) AND " +
	           "(:color IS NULL OR p.color = :color) AND " +
	           "(:size IS NULL OR p.size = :size) AND " +
	           "(:fabric IS NULL OR p.fabric = :fabric)")
	    List<ProductModel> findProductsByFilters(
	        @Param("minPrice") Double minPrice,
	        @Param("maxPrice") Double maxPrice,
	        @Param("color") String color,
	        @Param("size") String size,
	        @Param("fabric") String fabric
	    );
	
	 @Modifying
	 @Transactional
	 @Query("UPDATE ProductModel p SET p.availability = p.availability - :quantity WHERE p.productId = :productId AND p.availability >= :quantity")
	 int decreaseAvailability(@Param("productId") Long productId, @Param("quantity") int quantity);

}
