package com.duelmasters.DuelMastersServer.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.LoadFile;
import com.duelmasters.DuelMastersServer.Service.Interfaces.FileService;

@RestController
@CrossOrigin("*")
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
		return new ResponseEntity<>(fileService.uploadFile(file), HttpStatus.OK);
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {

		LoadFile file = fileService.downloadFile(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(new ByteArrayResource(file.getContent()));

	}

	@GetMapping("/download/bytes/{id}")
	public ResponseEntity<Object> downloadBytes(@PathVariable String id) throws IOException {
		return new ResponseEntity<>(fileService.downloadFile(id), HttpStatus.OK);
	}

	
	@GetMapping("/download/bytes2/{id}")
	public ResponseEntity<Object> downloadBytes2(@PathVariable String id) throws IOException {
		return new ResponseEntity<>(fileService.downloadFile(id).getContent(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id) throws IOException {
		fileService.deleteFile(id);
	}
	
}
