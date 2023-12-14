package com.example.demo.model;

import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class OrderItem {

	    public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long orderItemId;

	    public Long getOrderItemId() {
			return orderItemId;
		}

		public void setOrderItemId(Long orderItemId) {
			this.orderItemId = orderItemId;
		}

		private  int quantity;

	    private double price;

	    //private Date createdDate;

		 @ManyToOne
		 @JoinColumn(name = "order_orderId",nullable = false)
		 private OrderModel order;
		

	    @ManyToOne
	    @JoinColumn(name = "product_productId",nullable = false)
	    private ProductModel product;

		

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

//		public Date getCreatedDate() {
//			return createdDate;
//		}
//
//		public void setCreatedDate(Date createdDate) {
//			this.createdDate = createdDate;
//		}

//		public OrderModel getOrder() {
//			return order;
//		}
//
//		public void setOrder(OrderModel order) {
//			this.order = order;
//		}

		public ProductModel getProduct() {
			return product;
		}

		public void setProduct(ProductModel product) {
			this.product = product;
		}
}
