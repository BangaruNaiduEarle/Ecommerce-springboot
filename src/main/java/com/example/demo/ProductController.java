package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.model.CategoryModel;
import com.example.demo.model.ProductModel;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping
public class ProductController {
	 @Autowired
	 private ProductService productService;
	 @Autowired 
	 private CategoryRepo categoryRepo;
	 
	 private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	 
	 @GetMapping("/product/category/{categoryId}")
	 public ResponseEntity<List<ProductModel>> getProductsByCategory(@PathVariable Long categoryId) {
	      List<ProductModel> products = productService.getProductsByCategory(categoryId);
	      return new ResponseEntity<>(products, HttpStatus.OK);
	 }

		/*
		 * @PostMapping("/product/add") public ResponseEntity<?> addProduct(@RequestBody
		 * ProductModel product) { try { System.out.println("Received Product: " +
		 * product);
		 * 
		 * if (product.getCategory() != null && product.getCategory().getId() != null) {
		 * // Retrieve the category from the database CategoryModel category =
		 * categoryRepo.findById(product.getCategory().getId()).orElse(null);
		 * 
		 * if (category != null) { // Set the category in the product
		 * product.setCategory(category);
		 * 
		 * // Save the product ProductModel addedProduct =
		 * productService.addProduct(product); return new ResponseEntity<>(addedProduct,
		 * HttpStatus.CREATED); } else { //System.out.println("Category ID not found: "
		 * + product.getCategoryId());
		 * 
		 * return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND); } }
		 * else { return new ResponseEntity<>("CategoryId is required",
		 * HttpStatus.BAD_REQUEST); } } catch (Exception e) { e.printStackTrace();
		 * return new ResponseEntity<>("Error processing request",
		 * HttpStatus.INTERNAL_SERVER_ERROR); } }
		 */
	      //ProductModel addedProduct = productService.addProduct(product);
	     // return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
	 
	 @PostMapping("/product/add")
	 public ResponseEntity<String> addProduct(@RequestBody ProductModel productModel) {
		    try {
		        productService.addProduct(productModel);
		        return ResponseEntity.ok("Product added successfully");
		    } catch (CategoryNotFoundException e) {
		        logger.error("Category not found", e);

		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found cont");
		    }
		}
	 
	 
	 @PutMapping("/product/update/{productId}")
	 public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody ProductModel updatedProduct) {
	        if (productService.updateProduct(productId, updatedProduct)) {
	            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	        }
	 }

	 @DeleteMapping("/product/delete/{productId}")
	 public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
	        if (productService.deleteProduct(productId)) {
	            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	        }
	  }
	 @GetMapping("/product/getProductById/{productId}")
	 public ResponseEntity<ProductModel> getProductById(@PathVariable Long productId) {
	        ProductModel product = productService.getProductById(productId);
	        if (product != null) {
	            return new ResponseEntity<>(product, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
		/* 
		 * @GetMapping("/product/searchByName") public List<ProductModel>
		 * searchProductsByName(@RequestParam String productName) { return
		 * productService.searchProducts(productName); }
		 */
	 
	 @GetMapping("/product/search")
	 public ResponseEntity<List<ProductModel>> searchProducts(
	            @RequestParam(required = false) String productName,
	            @RequestParam(required = false) Long categoryId
	    ) {
	        List<ProductModel> searchResults = productService.searchProducts(productName, categoryId);
	        return new ResponseEntity<>(searchResults, HttpStatus.OK);
	    }

	 @GetMapping("/products/filter")
	 public ResponseEntity<List<ProductModel>> filterProducts(
	            @RequestParam(required = false) Double minPrice,
	            @RequestParam(required = false) Double maxPrice,
	            @RequestParam(required = false) String color,
	            @RequestParam(required = false) String size,
	            @RequestParam(required = false) String fabric) {

	        List<ProductModel> filteredProducts = productService.filterProducts(minPrice, maxPrice, color, size, fabric);

	        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
	    }

	 @GetMapping("/products/buy/{productId}/{quantity}")
	 public ResponseEntity<String> buyProduct(
	            @PathVariable Long productId,
	            @PathVariable int quantity) {

	        boolean success = productService.decreaseAvailability(productId, quantity);

	        if (success) {
	            return new ResponseEntity<>("Purchase successful!", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Purchase failed. Not enough availability.", HttpStatus.BAD_REQUEST);
	        }
	    }
	 
}
