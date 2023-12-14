package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CartModel;
import com.example.demo.service.CartService;

@RestController
@RequestMapping
public class CartController {
	 @Autowired
	 private CartService cartService;

	 @GetMapping("/cart/getUserCartItems/{userId}")
	 public ResponseEntity<List<CartModel>> getUserCartItems(@PathVariable Long userId) {
	    List<CartModel> cart = cartService.getUserCart(userId);
	    return new ResponseEntity<>(cart, HttpStatus.OK);
	 }
	 
//	 @PostMapping("/cart/addToCart")
//	 public ResponseEntity<CartModel> addToCart(@RequestBody Map<String, Object> request) {
//		 Long userId = Long.valueOf(request.get("userId").toString());
//		 Long productId = Long.valueOf(request.get("productId").toString());
//		 int quantity = Integer.parseInt(request.get("quantity").toString()); 
//		 CartModel cartItem = cartService.addToCart(userId, productId, quantity);
//	        if (cartItem != null) {
//	            return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
//	        } else {
//	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	        }
//	 }

	 @PostMapping("/cart/addToCart")
	 public ResponseEntity<CartModel> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
	     Long userId = addToCartRequest.getUserId();
	     Long productId = addToCartRequest.getProductId();
	     int quantity = addToCartRequest.getQuantity();

	     CartModel cartItem = cartService.addToCart(userId, productId, quantity);

	     if (cartItem != null) {
	         return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
	     } else {
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	     }
	 }
	 @DeleteMapping("/cart/removeFromCart")
     public ResponseEntity<String> removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
	        boolean result = cartService.removeFromCart(userId, productId);
	        if (result) {
	            return new ResponseEntity<>("Item removed from cart successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Item not found in cart", HttpStatus.NOT_FOUND);
	        }
	 }

}
