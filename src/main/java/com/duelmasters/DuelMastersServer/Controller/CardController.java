package com.duelmasters.DuelMastersServer.Controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.duelmasters.DuelMastersServer.Domain.DTO.CardDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.MapperDTO;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;
import com.duelmasters.DuelMastersServer.Service.DAO.FileService;

@RestController
//@AllArgsConstructor
@RequestMapping("/cards")
public class CardController {

	private CardService cardService;
	private FileService fileService;
	private MapperDTO mapper;

	@Autowired
	public CardController(CardService cardService, FileService fileService, MapperDTO mapperDTO) {
		this.cardService = cardService;
		this.fileService = fileService;
		this.mapper = mapperDTO;
	}
	
	@GetMapping(value = "/getOne/{cardId}")
	public ResponseEntity<Object> getCard(@PathVariable String cardId) {
		return new ResponseEntity<>(cardService.getCard(cardId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getOneWithImage/{cardId}")
	public ResponseEntity<Object> getCardWithImage(@PathVariable String cardId) throws IOException{
		return new ResponseEntity<>(cardService.getCardWithImage(cardId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getGameCard/{cardId}")
	public ResponseEntity<Object> getGameCard(@PathVariable String cardId) throws IOException{
		return new ResponseEntity<>(cardService.getGameCard(cardId), HttpStatus.OK);
	}

	@GetMapping
	public List<Card> getAll() {
		return cardService.getAllCards();
	}

	@PostMapping(value = "/add", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> createCard(@RequestPart Card card, @RequestPart("file") MultipartFile file)
			throws IOException {
//		byte[] byteArr = file.getBytes();
//		card.setImage(byteArr);
		card.setImageId(fileService.uploadFile(file));
		cardService.createCard(card);
		return new ResponseEntity<>(card, HttpStatus.OK);
	}

	@GetMapping(value = "/allLegendary")
	public ResponseEntity<Object> getAllLegendaryCards() {
		List<CardDTO> cards = cardService.getAllLegendaryCards().stream().map(mapper::cardToCardDTO).collect(Collectors.toList());
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allSuperRare")
	public ResponseEntity<Object> getAllSuperRareCards() {
		List<CardDTO> cards = cardService.getAllSuperRareCards().stream().map(mapper::cardToCardDTO).collect(Collectors.toList());
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allVeryRare")
	public ResponseEntity<Object> getAllVeryRareCards() {
		List<CardDTO> cards = cardService.getAllVeryRareCards().stream().map(mapper::cardToCardDTO).collect(Collectors.toList());
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allRare")
	public ResponseEntity<Object> getAllRareCards() {
		List<CardDTO> cards = cardService.getAllRareCards().stream().map(mapper::cardToCardDTO).collect(Collectors.toList());
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allUncommon")
	public ResponseEntity<Object> getAllUncommonCards() {
		List<CardDTO> cards = cardService.getAllUncommonCards().stream().map(mapper::cardToCardDTO).collect(Collectors.toList());
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allCommon")
	public ResponseEntity<Object> getAllCommonCards() {
		List<CardDTO> cards = cardService.getAllCommonCards().stream().map(mapper::cardToCardDTO).collect(Collectors.toList());
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}
	
	@GetMapping(value = "/random")
	public ResponseEntity<Object> getRandomCard() {
		CardDTO card = mapper.cardToCardDTO(cardService.getRandomCard());
		return new ResponseEntity<>(card, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void deleteCardById(@PathVariable String id) throws IOException {
		fileService.deleteFile(cardService.getCard(id).getImageId());
		cardService.deleteCard(id);
	}
}
