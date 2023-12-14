package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BannerModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bannerId;
    private String bannerName;
    private String bannerUrl;
    private String bannerLink;
	public Long getBannerId() {
		return bannerId;
	}
	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}
	public String getBannerName() {
		return bannerName;
	}
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	public String getBannerLink() {
	    return bannerLink;
	}
	public void setBannerLink(String bannerLink) {
	    this.bannerLink = bannerLink;
	}
	public BannerModel() {
	        // Default constructor
	}

    public BannerModel(String bannerName, String bannerLink) {
	        this.bannerName = bannerName;
	        this.bannerLink = bannerLink;
	}

}
