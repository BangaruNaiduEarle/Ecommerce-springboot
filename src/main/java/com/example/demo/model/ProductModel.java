package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class ProductModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long productId;

    private String productName;
    private String productDescription;
    private double productPrice;
    private String pictureUrl;
	private String color;
	
	private String fabric;
	private String size;
	
    @Enumerated(EnumType.STRING)
    private Availability availability; 
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderModel> orders;
    
	
    public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public enum Availability {
        IN_STOCK,
        OUT_OF_STOCK
    }
	//private Long categoryId; 
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;

    public String getFabric() {
		return fabric;
	}

	public void setFabric(String fabric) {
		this.fabric = fabric;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
	 public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}
	 
	/*
	 * public Long getCategoryId() { return categoryId; }
	 * 
	 * public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
	 */

}
