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

import com.example.demo.model.WishListModel;
import com.example.demo.service.WishListService;

@RestController
@RequestMapping
public class WishListController {
	@Autowired
	private WishListService wishlistservice;
	
	@GetMapping("/wishlist/getUserWishlist/{userId}")
	public ResponseEntity<List<WishListModel>> getUserWishlist(@PathVariable Long userId) {
	     List<WishListModel> wishlist = wishlistservice.getUserWishlist(userId);
	     return new ResponseEntity<>(wishlist, HttpStatus.OK);
	}

	/*
	 * @PostMapping("/wishlist/addItemtoWishlist") public
	 * ResponseEntity<WishListModel> addItemToWishlist(@RequestBody WishListModel
	 * wishlistmodel) { WishListModel addedItem =
	 * wishlistservice.addToWishlist(wishlistmodel); return new
	 * ResponseEntity<>(addedItem, HttpStatus.CREATED); }
	 */
	 @PostMapping("/wishlist/addItemToWishlist")
	    public ResponseEntity<WishListModel> addToWishlist(@RequestBody Map<String, Object> request) {
		 Long userId = Long.valueOf(request.get("userId").toString());
		 Long productId = Long.valueOf(request.get("productId").toString());
		 WishListModel addedItem = wishlistservice.addToWishlist(userId, productId);
	        if (addedItem != null) {
	            return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }

	@DeleteMapping("/wishlist/removeItemFromWishlist")
    public ResponseEntity<String> removeFromWishlist(@RequestParam Long userId, @RequestParam Long productId) {
        if (wishlistservice.removeItemFromWishlist(userId, productId)) {
            return new ResponseEntity<>("Item removed from wishlist successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to remove item from wishlist.", HttpStatus.BAD_REQUEST);
        }
    }
   /* public ResponseEntity<String> removeItemFromWishlist(@RequestParam Long userId, @RequestParam Long productId) {
	     wishlistservice.removeItemFromWishlist(userId, productId);
	     return new ResponseEntity<>("Item removed from wishlist successfully",HttpStatus.OK);
	}*/

}
