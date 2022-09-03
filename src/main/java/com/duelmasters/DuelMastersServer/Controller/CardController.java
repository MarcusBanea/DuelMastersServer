package com.duelmasters.DuelMastersServer.Controller;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping("/cards")
public class CardController {

    private CardService cardService;
    
    @Autowired
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

    @GetMapping
    public List<Card> getAll(){
        return cardService.getAllCards();
    }
    
    @PostMapping("/add")
    public ResponseEntity<Object> createCard(@RequestBody Card card){
    	cardService.createCard(card);
    	return new ResponseEntity<>(card, HttpStatus.OK);
    }
}
