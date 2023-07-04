package com.duelmasters.DuelMastersServer.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duelmasters.DuelMastersServer.Domain.DTO.CardInCollectionDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.MapperDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.UserCollectionDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.UserDTO;
import com.duelmasters.DuelMastersServer.Service.Interfaces.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	private MapperDTO mapper;
	
	@Autowired
	public UserController(UserService userService, MapperDTO mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable String id) {
		UserDTO user = mapper.userToUserDTO(userService.getUser(id));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/withCollection/{id}")
	public ResponseEntity<Object> findByIdWithCollection(@PathVariable String id) {
		UserCollectionDTO user = mapper.userToUserCollectionDTO(userService.getUser(id));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/openPack/{userId}")
	public ResponseEntity<Object> openPack(@PathVariable String userId, @RequestParam String packType) {
		List<String> cardsOfPack = userService.openPack(userId, packType);
		return new ResponseEntity<>(cardsOfPack, HttpStatus.OK);
	}
	
//	@GetMapping(value = "/getCollection/{userId}")
//	public ResponseEntity<Object> getUserCollection(@PathVariable String userId) {
//		HashMap<String, Integer> collection = userService.getUserCollection(userId);
//		return new ResponseEntity<>(collection, HttpStatus.OK);
//	}
	
	@GetMapping(value = "/getCollectionWithNames/{userId}")
	public ResponseEntity<Object> getUserCollectionWithNames(@PathVariable String userId) {
		HashMap<String, Integer> collection = userService.getUserCollectionWithNames(userId);
		return new ResponseEntity<>(collection, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCollection/{userId}")
	public ResponseEntity<Object> getUserCollectionCards(@PathVariable String userId) {
		List<CardInCollectionDTO> collection = userService.getUserCollectionCards(userId);
		return new ResponseEntity<>(collection, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getRandomDeck/{userId}")
	public ResponseEntity<Object> getUserRandomDeck(@PathVariable String userId) {
		return new ResponseEntity<>(userService.generateRandomDeckFromCollection(userId, 25), HttpStatus.OK);
	}
	
	//old
	@GetMapping(value = "/getRandomDeckWithGameCards/{userId}")
	public ResponseEntity<Object> getUserRandomDeckWithGameCards(@PathVariable String userId) throws IOException {
		return new ResponseEntity<>(userService.generateRandomDeckFromCollectionWithGameCards(userId, 5), HttpStatus.OK);
	}
	
	//new
	@GetMapping(value = "/getMatchDeck/{userId}")
	public ResponseEntity<Object> getMatchDeck(@PathVariable String userId) throws IOException {
		return new ResponseEntity<>(userService.generateRandomDeckFromCollectionWithGameCards(userId, 25), HttpStatus.OK);
	}
}
