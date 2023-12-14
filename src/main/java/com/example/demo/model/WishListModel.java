package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WishListModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wishListId;
	/*
	 * private Long productId; private Long userId;
	 */
	
	@ManyToOne
    @JoinColumn(name = "user_userId", nullable = false)
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "product_productId", nullable = false)
    private ProductModel product;

	public Long getWishListId() {
		return wishListId;
	}

	public void setWishListId(Long wishListId) {
		this.wishListId = wishListId;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}
	
	
	
	
}
