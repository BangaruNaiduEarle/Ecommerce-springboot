package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ContactDetailsModel;
import com.example.demo.service.ContactDetailsService;

@RestController
@RequestMapping
public class ContactDetailsController {
	 @Autowired
	    private ContactDetailsService contactDetailsService;

	    @GetMapping("/contact/getContactDetails")
	    public ResponseEntity<ContactDetailsModel> getContactDetails() {
	        Optional<ContactDetailsModel> detailsOptional = contactDetailsService.getContactDetails();

	        if (detailsOptional.isPresent()) {
	            return new ResponseEntity<>(detailsOptional.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	

}
