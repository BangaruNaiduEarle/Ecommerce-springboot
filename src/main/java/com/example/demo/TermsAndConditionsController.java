package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TermsAndConditionsModel;
import com.example.demo.service.TermsAndConditionsService;

@RestController
@RequestMapping
public class TermsAndConditionsController {
	@Autowired
    private TermsAndConditionsService termsAndConditionsService;

    @PostMapping("/termsAndConditions/agreeToTerms")
    public ResponseEntity<TermsAndConditionsModel> agreeToTerms(@RequestParam Long userId) {
        TermsAndConditionsModel agreement = termsAndConditionsService.agreeToTerms(userId);
        return new ResponseEntity<>(agreement, HttpStatus.OK);
    }

    @GetMapping("/termsnadConditions/getAllTermsAndConditions")
    public ResponseEntity<List<TermsAndConditionsModel>> getAllTermsAndConditions() {
        List<TermsAndConditionsModel> allTerms = termsAndConditionsService.getAllTermsAndConditions();
        return new ResponseEntity<>(allTerms, HttpStatus.OK);
    }
	
}
