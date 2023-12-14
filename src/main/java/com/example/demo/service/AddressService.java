package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AddressModel;
import com.example.demo.repo.AddressRepo;

@Service
public class AddressService {
	 @Autowired
	 private AddressRepo addressRepo;
	 private static final Logger logger = LoggerFactory.getLogger(AddressService.class);


	 public List<AddressModel> getAllAddresses() {
		 try {
		        return addressRepo.findAll();
		    } catch (Exception e) {
	            logger.error("An error occurred while getting all addresses", e);
		        // You can also throw a custom exception or return an empty list, depending on your use case.
		        return Collections.emptyList();
		    }
	 }	

	 public AddressModel getAddressById(Long addressId) {
	        return addressRepo.findById(addressId).orElse(null);
	 }

	 public List<AddressModel> getAddressesByUserId(Long userId) {
	        return addressRepo.findByUserUserId(userId);
	 }

	 public void deleteAddress(Long addressId) {
	        addressRepo.deleteById(addressId);
	 }
	 public AddressModel saveAddress(AddressModel address){
	        return addressRepo.save(address);
	 }

}
