package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TermsAndConditionsModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String tandCtext;
    private LocalDateTime agreedDateTime;
    
    public TermsAndConditionsModel() {
        // Default constructor for JPA
    }

    public TermsAndConditionsModel(Long userId) {
        this.userId = userId;
        // Initialize other fields as needed
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTandCtext() {
		return tandCtext;
	}
	public void setTandCtext(String tandCtext) {
		this.tandCtext = tandCtext;
	}
	public LocalDateTime getAgreedDateTime() {
		return agreedDateTime;
	}
	public void setAgreedDateTime(LocalDateTime agreedDateTime) {
		this.agreedDateTime = agreedDateTime;
	}
    

}
