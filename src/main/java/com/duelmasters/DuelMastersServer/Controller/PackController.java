package com.duelmasters.DuelMastersServer.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Pack;
import com.duelmasters.DuelMastersServer.Service.Interfaces.PackService;

@RestController
@RequestMapping("/packs")
public class PackController {

	private PackService packService;

	@Autowired
	public PackController(PackService packService) {
		this.packService = packService;
	}

	@GetMapping
	public List<Pack> getAll() {
		return packService.getAllPacks();
	}

	@PostMapping(value = "/add", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> createPack(@RequestPart Pack pack, @RequestPart("file") MultipartFile file)
			throws IOException {
		byte[] byteArr = file.getBytes();
		pack.setImage(byteArr);
		packService.createPack(pack);
		return new ResponseEntity<>(pack, HttpStatus.OK);
	}
	
	@GetMapping(value = "/open/{packType}")
	public ResponseEntity<Object> openPack(@PathVariable String packType) {
		List<Card> cardsOfPack = packService.openPackType(packType);
		return new ResponseEntity<>(cardsOfPack, HttpStatus.OK);
	}

}
