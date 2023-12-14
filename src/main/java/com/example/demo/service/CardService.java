package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CardModel;
import com.example.demo.repo.CardRepo;

@Service
public class CardService {
	 @Autowired
	    private CardRepo cardRepo;

	    public CardModel saveCard(CardModel card) {
	        return cardRepo.save(card);
	    }

	    public List<CardModel> getCardsByUserId(Long userId) {
	        // Implement logic to retrieve cards by user ID
	        // For simplicity, let's assume a direct mapping between User and Card
	        // You may need to adjust this based on your actual data model
	        // E.g., cardRepository.findByUserId(userId)
	        return cardRepo.findAll();
	    }

	    public void deleteCard(Long cardId) {
	        cardRepo.deleteById(cardId);
	    }


}
