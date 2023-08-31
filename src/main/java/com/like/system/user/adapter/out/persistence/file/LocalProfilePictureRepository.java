package com.like.system.user.adapter.out.persistence.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.adapter.out.file.LocalFileRepository;
import com.like.system.file.adapter.out.file.LocalFileRepository.FileUploadLocation;
import com.like.system.user.domain.ProfilePictureRepository;

@Repository
public class LocalProfilePictureRepository implements ProfilePictureRepository {

	private LocalFileRepository localFileRepository;
	
	public LocalProfilePictureRepository(LocalFileRepository localFileRepository) {
		this.localFileRepository = localFileRepository;
	}
	
	@Override
	public String upload(MultipartFile sourceFile) {
		String fileName = UUID.randomUUID().toString();
		try {
			localFileRepository.fileTransfer(sourceFile, fileName, FileUploadLocation.STATIC_PATH);
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
			localFileRepository.deleteStaticFile(path);
		} catch (FileNotFoundException e) {				
			e.printStackTrace();
		}		
	}



}