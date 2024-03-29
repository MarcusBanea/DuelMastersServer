package com.duelmasters.DuelMastersServer.Service.Implementation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.LoadFile;
import com.duelmasters.DuelMastersServer.Service.Interfaces.FileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private GridFsTemplate template;

	@Autowired
	private GridFsOperations operations;

	@Override
	public String uploadFile(MultipartFile file) throws IOException {

		DBObject data = new BasicDBObject();
		data.put("size", file.getSize());

		Object fileId = template.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), data);

		return fileId.toString();
	}
	
	@Override
	public String uploadFileFromServer(byte[] fileData, String fileName, long fileSize) throws IOException {
		DBObject data = new BasicDBObject();
		data.put("size", fileSize);
		
		InputStream stream = new ByteArrayInputStream(fileData);
		Object fileId = template.store(stream, fileName, "image/jpeg", data);

		return fileId.toString();
	}

	@Override
	public LoadFile downloadFile(String id) throws IOException {
		GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));
		LoadFile file = new LoadFile();
		if (gridFSFile != null && gridFSFile.getMetadata() != null) {
			file.setName(gridFSFile.getFilename());
			file.setType(gridFSFile.getMetadata().get("_contentType").toString());
			file.setSize(gridFSFile.getMetadata().get("size").toString());
			file.setContent(operations.getResource(gridFSFile).getInputStream().readAllBytes());
		}
		return file;
	}
	
	@Override
	public String getFileIdByName(String name) throws IOException {
		
		GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("filename").is(name)));
		LoadFile file = new LoadFile();
		
		String id = "";
		if (gridFSFile != null && gridFSFile.getMetadata() != null) {
			id = gridFSFile.getObjectId().toString();
		}

		return id;
	}

	@Override
	public void deleteFile(String id) throws IOException {
		template.delete(new Query(Criteria.where("_id").is(id)));
	}
}
