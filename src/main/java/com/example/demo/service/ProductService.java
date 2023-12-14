package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.model.CategoryModel;
import com.example.demo.model.ProductModel;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.ProductRepo;

@Service
public class ProductService {
	 @Autowired
	 private ProductRepo productRepo;

	 @Autowired
	 private CategoryRepo categoryRepo;
	 
	 public List<ProductModel> getProductsByCategory(Long categoryId) {
	        return productRepo.findByCategoryId(categoryId);
	    }
	 
	 public List<ProductModel> getAllProducts() {
	        return productRepo.findAll();
	    }

		/*
		 * public ProductModel addProduct(ProductModel product) { return
		 * productRepo.save(product); }
		 */

	 public void addProduct(ProductModel productModel) throws CategoryNotFoundException {
	        // Check if the category exists
	        Optional<CategoryModel> categoryOptional = categoryRepo.findById(productModel.getCategory().getId());

	        //CategoryModel category = categoryRepo.findById(productModel.getCategory().getId())
	                //orElseThrow(() -> new CategoryNotFoundException("Category not found"));
	        
	        // Set the category for the product
	        //productModel.setCategory(category);
	                if (categoryOptional.isPresent()) {
	                    // Category exists, proceed with adding the product
	                    // Your product saving logic goes here
	                	productRepo.save(productModel);
	                } else {
	                    // Category not found, throw an exception
	                    throw new CategoryNotFoundException("Category not found service");
	                }
	                
	                
	        // Save the product
	        
	    }
	 public boolean updateProduct(Long productId, ProductModel updatedProduct) {
	        if (productRepo.existsById(productId)) {
	            updatedProduct.setProductId(productId);
	            productRepo.save(updatedProduct);
	            return true;
	        }
	        return false;
	    }

	 public boolean deleteProduct(Long productId) {
	        if (productRepo.existsById(productId)) {
	            productRepo.deleteById(productId);
	            return true;
	        }
	        return false;
	    }

	 public ProductModel getProductById(Long productId) {
	        return productRepo.findById(productId).orElse(null);
	    }
	 
		/*
		 * public List<ProductModel> searchProducts(String productName) { // You can add
		 * more logic here if needed return
		 * productRepo.findByProductNameContaining(productName); }
		 */
	 public List<ProductModel> searchProducts(String productName, Long categoryId) {
	        if (productName != null && !productName.isEmpty()) {
	            // Custom logic: Convert the product name to upper case before searching
	            productName = productName.toUpperCase();

	            if (categoryId != null) {
	                // Search by both product name and category
	                return productRepo.findByProductNameContainingAndCategoryId(productName, categoryId);
	            } else {
	                // Search only by product name
	                return productRepo.findByProductNameContaining(productName);
	            }
	        } else if (categoryId != null) {
	            // Search only by category
	            return productRepo.findByCategoryId(categoryId);
	        } else {
	            // Handle invalid input or return a default result
	            return List.of();
	        }
	    }
	 public List<ProductModel> filterProducts(Double minPrice, Double maxPrice, String color, String size, String fabric) {
	        return productRepo.findProductsByFilters(minPrice, maxPrice, color, size, fabric);
	    }

	 public boolean decreaseAvailability(Long productId, int quantity) {
	        return productRepo.decreaseAvailability(productId, quantity) > 0;
	    }
	 
	 
}
