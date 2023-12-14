package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ReviewsAndRatingsModel;
@Repository
public interface ReviewsAndRatingsRepo extends JpaRepository<ReviewsAndRatingsModel,Long>{

	List<ReviewsAndRatingsModel> findByProductProductId(Long productId);

}
