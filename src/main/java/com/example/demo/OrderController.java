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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CartModel;
import com.example.demo.model.OrderModel;
import com.example.demo.model.ProductModel;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;


@RestController
@RequestMapping
public class OrderController {
	 @Autowired
	    private OrderService orderService;
	 @Autowired
	 private CartService cartService;
	 private ProductService productService;
	 @Autowired
	    public OrderController(OrderService orderService) {
	        this.orderService = orderService;
	    }
	 public OrderController(OrderService orderService, ProductService productService) {
	        this.orderService = orderService;
	        this.productService = productService;
	    }
	    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

//	    @PostMapping("/order/calculateTotalPrice")
//	    public ResponseEntity<Double> calculateTotalPrice(@RequestBody CalculateTotalPriceRequest request) {
//	        double totalPrice = orderService.calculateTotalPriceList(request.getUserId(), request.getOrderItems());
//	        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
//	    }

	    @GetMapping("/order/getAllOrdersByUserId/{userId}")
	    public ResponseEntity<List<OrderModel>> getAllOrdersByUserId(@PathVariable Long userId) {
	        List<OrderModel> orders = orderService.getAllOrdersByUserId(userId);
	        return new ResponseEntity<>(orders, HttpStatus.OK);
	    }

//	    @PostMapping("/order/addOrder/{userId}")
//	    public ResponseEntity<OrderModel> addOrder(@PathVariable Long userId, @RequestBody OrderRequest orderRequest) {
//	        OrderModel newOrder = orderService.addOrder(userId, orderRequest);
//	        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
//	    }
//
//	    @PostMapping("/user/addMultiple/{userId}")
//	    public ResponseEntity<List<OrderModel>> addMultipleOrders(@PathVariable Long userId, @RequestBody List<OrderRequest> orderRequests) {
//	        List<OrderModel> newOrders = orderService.addMultipleOrders(userId, orderRequests);
//	        return new ResponseEntity<>(newOrders, HttpStatus.CREATED);
//	    }
	    
//	    @PostMapping("/placeOrder/{userId}")
//	    public ResponseEntity<OrderModel> placeOrder(@PathVariable Long userId) {
//	        OrderModel order = orderService.placeOrder(userId);
//
//	        // Assuming you have a method to clear the user's cart after placing the order
//	        cartService.clearCart(userId);
//
//	        return new ResponseEntity<>(order, HttpStatus.CREATED);
//	    }
	    

	    
	    @PostMapping("/order/placeOrder")
	    public ResponseEntity<Object> placeOrder(@RequestParam Long userId) {
	        try {
	            orderService.placeOrderList(userId);
	            return new ResponseEntity<>("Order placed successfully", HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error placing order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
	        }
	    }
	    
	    
	    
	    @DeleteMapping("/order/cancel/{orderId}")
	    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
	        orderService.cancelOrder(orderId);
	        return new ResponseEntity<>("Order canceled successfully", HttpStatus.OK);
	    }

	    @GetMapping("/order/getNumberOfOrdersForProduct/{productId}/count")
	    public ResponseEntity<Long> getNumberOfOrdersForProduct(@PathVariable Long productId) {
	        Long productCount = orderService.getNumberOfOrdersForProduct(productId);
	        return new ResponseEntity<>(productCount, HttpStatus.OK);
	    }

}
