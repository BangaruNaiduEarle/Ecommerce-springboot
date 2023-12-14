package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductShareLinkModel;
import com.example.demo.repo.ProductShareLinkRepo;

@Service
public class ProductShareLinkService {
	 @Autowired
	 private ProductShareLinkRepo productShareLinkRepo;

	 public String generateShareLink(Long productId) {
	        // Logic to generate a unique share link
	        String shareLink = "http://yourdomain.com/share/" + productId;

	        // Save the link in the database
	        ProductShareLinkModel productShareLink = new ProductShareLinkModel();
	        productShareLink.setProductId(productId);
	        productShareLink.setShareLink(shareLink);
	        productShareLinkRepo.save(productShareLink);

	        return shareLink;
	 }

	 public String getShareLink(Long productId) {
	        // Retrieve the share link from the database based on the productId
	        ProductShareLinkModel productShareLink = productShareLinkRepo.findByProductId(productId);
	        return productShareLink != null ? productShareLink.getShareLink() : null;
	 }

}
