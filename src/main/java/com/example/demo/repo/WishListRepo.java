package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.WishListModel;
@Repository
public interface WishListRepo extends JpaRepository<WishListModel, Long> {

	List<WishListModel> findByUserUserId(Long userId);

	void deleteByUserUserIdAndProductProductId(Long userId, Long productId);

}
