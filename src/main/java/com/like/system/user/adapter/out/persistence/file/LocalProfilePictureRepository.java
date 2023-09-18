package com.like.system.user.adapter.out.persistence.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.adapter.out.file.WebServerStaticRepository;
import com.like.system.user.domain.ProfilePictureRepository;

@Repository
public class LocalProfilePictureRepository implements ProfilePictureRepository {

	private WebServerStaticRepository repository;
	
	public LocalProfilePictureRepository(WebServerStaticRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public String upload(MultipartFile sourceFile) {
		String fileName = UUID.randomUUID().toString();
		try {
			repository.fileTransfer(sourceFile, fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	
	}

	@Override
	public void delete(String path) {
		try {
			repository.deleteStaticFile(path);
		} catch (FileNotFoundException e) {				
			e.printStackTrace();
		}		
	}



}
