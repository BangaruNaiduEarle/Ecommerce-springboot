package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TermsAndConditionsModel;
import com.example.demo.repo.TermsAndConditionsRepo;

@Service
public class TermsAndConditionsService {
	@Autowired
    private TermsAndConditionsRepo termsAndConditionsRepo;

    public TermsAndConditionsModel agreeToTerms(Long userId) {
        Optional<TermsAndConditionsModel> existingTerms = termsAndConditionsRepo.findByUserId(userId);

        if (existingTerms.isPresent()) {
            // User has already agreed to terms, handle accordingly
            return existingTerms.get();
        }

        TermsAndConditionsModel termsAndConditions = new TermsAndConditionsModel(userId);
        return termsAndConditionsRepo.save(termsAndConditions);
    }

    public List<TermsAndConditionsModel> getAllTermsAndConditions() {
        return termsAndConditionsRepo.findAll();
    }

}
