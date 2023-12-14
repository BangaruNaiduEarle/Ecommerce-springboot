package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProductShareLinkService;

@RestController
@RequestMapping
public class ProductShareLinkController {
	@Autowired
    private ProductShareLinkService productShareLinkService;

    @PostMapping("/productShareLink/generateShareLink/{productId}")
    public ResponseEntity<String> generateShareLink(@PathVariable Long productId) {
        String shareLink = productShareLinkService.generateShareLink(productId);
        return new ResponseEntity<>(shareLink, HttpStatus.OK);
    }

    @GetMapping("/productShareLink/getShareLink/{productId}")
    public ResponseEntity<String> getShareLink(@PathVariable Long productId) {
        String shareLink = productShareLinkService.getShareLink(productId);
        if (shareLink != null) {
            return new ResponseEntity<>(shareLink, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Share link not found", HttpStatus.NOT_FOUND);
        }
    }

}
