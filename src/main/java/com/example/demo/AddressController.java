package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AddressModel;
import com.example.demo.service.AddressService;

@RestController
@RequestMapping
public class AddressController {
	 @Autowired
	 private AddressService addressService;

	 @GetMapping("/address/getAlltheAddress")
	 public ResponseEntity<List<AddressModel>> getAllTheAddresses() {
		 List<AddressModel> addresses = addressService.getAllAddresses();
	     return new ResponseEntity<>(addresses, HttpStatus.OK);
	     //return addressService.getAllAddresses();
	 }

	 @GetMapping("/address/getTheAddressById/{id}")
	 public ResponseEntity<AddressModel> getTheAddressById(@PathVariable Long id) {
	     AddressModel address = addressService.getAddressById(id);
	     return address != null ? new ResponseEntity<>(address, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }
	 
	 @GetMapping("/address/getTheAddressByUserId/{userId}")
	 public ResponseEntity<List<AddressModel>> getTheAddressesByUserId(@PathVariable Long userId) {
	     List<AddressModel> addresses = addressService.getAddressesByUserId(userId);
	     return addresses.isEmpty() ?
	     new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	     new ResponseEntity<>(addresses, HttpStatus.OK);
	 }

	 @PostMapping("/address/addAddress/{userId}")
	 public ResponseEntity<AddressModel> addAddress(@PathVariable Long userId, @RequestBody AddressModel address) {
	        // Retrieve user by userId and set it to the address
	        // Set other address properties
	        // Save the address
	     AddressModel newAddress = addressService.saveAddress(address);
	     return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
	 }
	 
	 @DeleteMapping("/address/deleteAddress/{id}")
	 public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
	     addressService.deleteAddress(id);
	     return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
	 }
	 

}
