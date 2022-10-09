package com.duelmasters.DuelMastersServer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duelmasters.DuelMastersServer.Domain.DTO.MapperDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.UserCollectionDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.UserDTO;
import com.duelmasters.DuelMastersServer.Service.DAO.UserService;

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
	
}
