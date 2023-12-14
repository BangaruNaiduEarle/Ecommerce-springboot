package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemDto;
import com.example.demo.model.CartModel;
import com.example.demo.model.ProductModel;
import com.example.demo.model.UserModel;
import com.example.demo.repo.CartRepo;

import jakarta.transaction.Transactional;

@Service
public class CartService {
	@Autowired
	private CartRepo cartRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<CartModel> getUserCart(Long userId) {
    	UserModel user = userService.getUserById(userId);
        
        if (user != null) {
            return cartRepo.findByUser(user);
        }
        
        return Collections.emptyList();
      //return cartRepo.findByUserUserId(userId);
    }
    
    public CartDto listCartItems(Long userId) {
        UserModel user = userService.getUserById(userId);

        if (user != null) {
            List<CartModel> cartList = cartRepo.findByUser(user);

            List<CartItemDto> cartItems = new ArrayList<>();
            for (CartModel cart : cartList) {
                CartItemDto cartItemDto = getCartItemDto(cart);
                cartItems.add(cartItemDto);
            }

            double totalCost = calculateTotalCost(cartItems);

            return new CartDto(cartItems, totalCost);
        }

        // If the user is not found, return an empty CartDto
        return new CartDto(new ArrayList<>(), 0);
    }

    private CartItemDto getCartItemDto(CartModel cart) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cart.getProduct().getProductId());
        cartItemDto.setProductName(cart.getProduct().getProductName());
        cartItemDto.setQuantity(cart.getQuantity());
        cartItemDto.setPrice(cart.getProduct().getProductPrice());
        return cartItemDto;
    }

    private double calculateTotalCost(List<CartItemDto> cartItems) {
        double totalCost = 0;
        for (CartItemDto cartItem : cartItems) {
            totalCost += cartItem.getPrice() * cartItem.getQuantity();
        }
        return totalCost;
    }
    
    
    public CartModel addToCart(Long userId, Long productId, int quantity) {
    	if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer");
        }
      UserModel user = userService.getUserById(userId);
      ProductModel product = productService.getProductById(productId);

      if (user != null && product != null) {
            CartModel cartItem = new CartModel();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            return cartRepo.save(cartItem);
      }
      else {
           return null; // User or Product not found
      }
    }
    
    public double calculateTotalPrice(List<CartModel> cartItems) {
        // Implement logic to calculate the total price for cart items
        double totalPrice = 0.0;
        for (CartModel cartItem : cartItems) {
            totalPrice += cartItem.getProduct().getProductPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }
    
    public boolean removeFromCart(Long userId, Long productId) {
        cartRepo.deleteByUserUserIdAndProductProductId(userId, productId);
        return true; // Assuming successful deletion
    }
    public void clearCart(Long userId) {
        // Implement logic to clear the user's cart after placing the order
        cartRepo.deleteByUserUserId(userId);
    }

}

