package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class OrderModel {
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;	
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
	
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    
    @Column(name = "generated_order_id")
    private String generatedOrderId; // Custom order ID based on date and user ID

    public void setGeneratedOrderId(String generatedOrderId) {
        this.generatedOrderId = generatedOrderId;
    }
	


    
    public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}



	@ManyToOne
    @JoinColumn(name = "product_productId", nullable = false)
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "user_userId", nullable = false)
    private UserModel user;
    


//	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<OrderItem> orderItems = new ArrayList<>();
	
	 public String getGeneratedOrderId() {
		return generatedOrderId;
	}
	
//	public List<OrderItem> getOrderItems() {
//	        return orderItems;
//	    }
//
//	    public void setOrderItems(List<OrderItem> orderItems) {
//	        this.orderItems = orderItems;
//	    }



	private int quantity;
    private double totalPrice;

	
	
	public OrderModel() {
		// TODO Auto-generated constructor stub
		
	}
	 public OrderModel(UserModel user) {
	        this.orderDate = LocalDateTime.now();
	        this.generatedOrderId = generateOrderId(user.getUserId(), orderDate);
	        // ... other initializations ...
	    }
	
	
	public String generateOrderId(Long userId, LocalDateTime orderDate) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	        String formattedDate = orderDate.format(formatter);
	        return String.format("%s%d", formattedDate, userId);
	    }
    
}