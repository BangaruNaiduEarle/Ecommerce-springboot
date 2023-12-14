package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CartModel;
import com.example.demo.model.UserModel;
@Repository
public interface CartRepo extends JpaRepository<CartModel, Long> {
	List<CartModel> findByUserUserId(Long userId);
    void deleteByUserUserIdAndProductProductId(Long userId, Long productId);
	void deleteByUserUserId(Long userId);
	List<CartModel> findByUser(UserModel user);

}