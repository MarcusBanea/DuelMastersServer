package com.duelmasters.DuelMastersServer.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;

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
    
    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    public ResponseEntity<Object> createCard(@RequestPart Card card, 
    		@RequestPart("file") MultipartFile file) throws IOException{
    	byte [] byteArr=file.getBytes();
    	card.setImage(byteArr);
    	cardService.createCard(card);
    	return new ResponseEntity<>(card, HttpStatus.OK);
    }
}
