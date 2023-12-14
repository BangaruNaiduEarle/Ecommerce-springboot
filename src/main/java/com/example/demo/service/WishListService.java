package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.WishListModel;
import com.example.demo.repo.WishListRepo;

import jakarta.transaction.Transactional;

@Service
public class WishListService {
	@Autowired
	private WishListRepo wishlistrepo;
	@Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;
	
    public List<WishListModel> getUserWishlist(Long userId) {
        return wishlistrepo.findByUserUserId(userId);
    }

	/*
	 * public WishListModel addItemToWishlist(WishListModel wishlistmodel) { //
	 * Additional validation or business logic can be added here return
	 * wishlistrepo.save(wishlistmodel); }
	 */
    public WishListModel addToWishlist(Long userId, Long productId) {
    	UserModel user = userService.getUserById(userId);
        ProductModel product = productService.getProductById(productId);
        
        if (user != null && product != null) {
        WishListModel wishListItem = new WishListModel();
        wishListItem.setUser(user); // You may need to adjust how you create a UserModel instance
        wishListItem.setProduct(product);// You may need to adjust how you create a ProductModel instance
        
        return wishlistrepo.save(wishListItem);
        }
        else {
        	return null;
        }
    }
   
	/*
	 * public void removeItemFromWishlist(Long wishListId) { // Additional
	 * validation or business logic can be added here
	 * wishlistrepo.deleteById(wishListId); }
	 */
    @Transactional
    public boolean removeItemFromWishlist(Long userId, Long productId) {
        wishlistrepo.deleteByUserUserIdAndProductProductId(userId, productId);
        return true;
    }
	

}
