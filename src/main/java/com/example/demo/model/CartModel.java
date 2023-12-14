package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;			
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CartId;
	
	@ManyToOne
    @JoinColumn(name = "user_userId", nullable = false)
	private UserModel user;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_productId")
	private ProductModel product;
	@ManyToOne
    @JoinColumn(name = "order_orderId")
    private OrderModel order;
	private int quantity;
	
	
	
	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	

	public Long getCartId() {
		return CartId;
	}

	public void setCartId(Long cartId) {
		CartId = cartId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
