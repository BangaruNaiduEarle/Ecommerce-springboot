package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ContactDetailsModel;
import com.example.demo.repo.ContactDetailsRepo;

@Service
public class ContactDetailsService {
	@Autowired
    private ContactDetailsRepo contactDetailsRepo;

    public Optional<ContactDetailsModel> getContactDetails() {
        // Assuming there's only one set of contact details
        // Adjust the logic based on your actual requirements
        return contactDetailsRepo.findById(1L);
    }

}
