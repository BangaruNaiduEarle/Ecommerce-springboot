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

import com.example.demo.model.CardModel;
import com.example.demo.service.CardService;

@RestController
@RequestMapping
public class CardController {
	@Autowired
    private CardService cardService;

    @PostMapping("/card/saveCard")
    public ResponseEntity<CardModel> saveCard(@RequestBody CardModel card) {
        CardModel savedCard = cardService.saveCard(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    @GetMapping("/card/getCardsByUserId/{userId}")
    public ResponseEntity<List<CardModel>> getCardsByUserId(@PathVariable Long userId) {
        List<CardModel> cards = cardService.getCardsByUserId(userId);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @DeleteMapping("/card/deleteCard/{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
        return new ResponseEntity<>("Card deleted successfully", HttpStatus.OK);
    }


}
