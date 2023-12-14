package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class ReviewsAndRatingsModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
	@ManyToOne
    @JoinColumn(name = "product_productId", nullable = false)
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "user_userId", nullable = false)
    private UserModel user;

    private String text;

    private int rating;

    
    @Lob
    private byte[] reviewImage;

    @Lob
    private byte[] reviewVideo;

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public byte[] getReviewImage() {
		return reviewImage;
	}

	public void setReviewImage(byte[] reviewImage) {
		this.reviewImage = reviewImage;
	}

	public byte[] getReviewVideo() {
		return reviewVideo;
	}

	public void setReviewVideo(byte[] reviewVideo) {
		this.reviewVideo = reviewVideo;
	}
}
