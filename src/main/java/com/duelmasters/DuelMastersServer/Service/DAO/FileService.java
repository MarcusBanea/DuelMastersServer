package com.duelmasters.DuelMastersServer.Service.DAO;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.LoadFile;

public interface FileService {

	public String uploadFile(MultipartFile file) throws IOException;
	
	public String uploadFileFromServer(byte[] fileData, String fileName, long fileSize) throws IOException;
	
	public LoadFile downloadFile(String id) throws IOException;
	
	public void deleteFile(String id) throws IOException;
	
	public String getFileIdByName(String name) throws IOException;
	
}
