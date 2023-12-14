package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderModel;
@Repository
public interface OrderRepo extends JpaRepository<OrderModel, Long> {

	List<OrderModel> findByUserUserId(Long userId);
	
	 Long countByProductProductId(Long productId);

}
