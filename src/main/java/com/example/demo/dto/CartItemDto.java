package com.example.demo.dto;

import com.example.demo.model.ProductModel;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CartItemDto {
	
	    private Long productId;
	    private String productName;
	    private int quantity;
	    private double price;

		 @ManyToOne 
		 @JoinColumn(name = "product_productId") 
		 private ProductModel product; // 
		 public CartItemDto(Long productId, String productName, int quantity, double price) { 
		  this.product = new ProductModel(); 
		  this.product.setProductId(productId);
		  }
		 
		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getPrice() {
			return price;
		}
//		public CartItemDto(Long productId, String productName, int quantity, double price) {
//			super();
//			this.productId = productId;
//			this.productName = productName;
//			this.quantity = quantity;
//			this.price = price;
//		}
		public void setPrice(double price) {
			this.price = price;
		}
		public CartItemDto() {
	    }
//		public ProductModel getProduct() {
//			// TODO Auto-generated method stub
//			return product;
//		}

}
