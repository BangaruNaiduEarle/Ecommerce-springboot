package com.example.demo.service;

import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemDto;
import com.example.demo.model.OrderModel;
import com.example.demo.model.ProductModel;
import com.example.demo.model.UserModel;
import com.example.demo.repo.OrderRepo;
//import com.example.demo.repo.ProductRepo;


@Service("customOrderService")
public class OrderService {
	
	    @Autowired
	    private OrderRepo orderRepo; 

	    @Autowired
	    private ProductService productService;
	    
	    @Autowired
	    private CartService cartService;
	    
	    @Autowired
	    private UserService userService;
	    
	    
 
	    public void placeOrderList(Long userId) {
	        // Get cart items for the user
	        CartDto cartDto = cartService.listCartItems(userId);

	        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();

	        // Create the order and save it
	        OrderModel newOrder = new OrderModel();
	        UserModel user = userService.getUserById(userId);
	        newOrder.setUser(user);
	        newOrder.setOrderDate(LocalDateTime.now()); // Set the order date

	        //newOrder.setUser(new UserModel(userId)); // Assuming you have a method to get a user by ID
	        newOrder.setTotalPrice(cartDto.getTotalCost());		
	        newOrder.setQuantity(cartItemDtoList.get(0).getQuantity());
	        newOrder.setGeneratedOrderId(newOrder.generateOrderId(user.getUserId(), newOrder.getOrderDate()));

	        ProductModel product = productService.getProductById(cartItemDtoList.get(0).getProductId());
	        if (product != null) {
	            newOrder.setProduct(product);
	        } else {
	            // Handle the case where the product is null
	            System.out.println("Product is null for CartItemDto: " + cartItemDtoList.get(0).getProductId());
	        }
	        orderRepo.save(newOrder);

			/*
			 * for (CartItemDto cartItemDto : cartItemDtoList) {
			 * 
			 * // Create order item and save each one ProductModel itemproduct =
			 * productService.getProductById(cartItemDto.getProductId()); if (itemproduct !=
			 * null) { OrderItem existingOrderItem =
			 * orderItemRepo.findByOrderAndProduct(newOrder, itemproduct);
			 * 
			 * if (existingOrderItem == null) { OrderItem newOrderItem = new OrderItem(); //
			 * Set other properties newOrderItem.setProduct(itemproduct);
			 * newOrderItem.setQuantity(cartItemDto.getQuantity());
			 * newOrderItem.setOrder(newOrder); orderItemRepo.save(newOrderItem);
			 * 
			 * } else { // Handle the case where OrderItem already exists int newQuantity =
			 * calculateNewQuantity(existingOrderItem.getQuantity(),
			 * cartItemDto.getQuantity()); existingOrderItem.setQuantity(newQuantity);
			 * orderItemRepo.save(existingOrderItem);
			 * System.out.println("OrderItem already exists for productId: " +
			 * itemproduct.getProductId()); }
			 * 
			 * 
			 * }
			 */
	        }

	        // Clear the user's cart after placing the order
	        //cartService.clearCart(userId);
	    
			/*
			 * public int calculateNewQuantity(int existingQuantity, int additionalQuantity)
			 * { // Your logic to calculate the new quantity based on existing and
			 * additional quantity return existingQuantity + additionalQuantity; }
			 * 
			 * 
			 * 
			 * @Transactional public double calculateTotalPriceList(Long userId,
			 * List<OrderProductQuantity> orderItems) { double totalPrice = 0.0;
			 * 
			 * for (OrderProductQuantity orderItem : orderItems) { ProductModel product =
			 * productService.getProductById(orderItem.getProductId()); if (product != null)
			 * { totalPrice += product.getProductPrice() * orderItem.getQuantity(); } }
			 * return totalPrice; }
			 */

	    
	    public List<OrderModel> getAllOrdersByUserId(Long userId) {
	        return orderRepo.findByUserUserId(userId);
	    }


	    public void cancelOrder(Long orderId) {
	        orderRepo.deleteById(orderId);
	    }

	    public Long getNumberOfOrdersForProduct(Long productId) {
	        return orderRepo.countByProductProductId(productId);
	    }
	    
}	   