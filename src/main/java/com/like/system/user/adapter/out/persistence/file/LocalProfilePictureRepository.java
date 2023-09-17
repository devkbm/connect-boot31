package com.like.system.user.adapter.out.persistence.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.adapter.out.file.FileServerRepository;
import com.like.system.file.adapter.out.file.FileUploadLocation;
import com.like.system.user.domain.ProfilePictureRepository;

@Repository
public class LocalProfilePictureRepository implements ProfilePictureRepository {

	private FileServerRepository localFileRepository;
	
	public LocalProfilePictureRepository(FileServerRepository localFileRepository) {
		this.localFileRepository = localFileRepository;
	}
	
	@Override
	public String upload(MultipartFile sourceFile) {
		String fileName = UUID.randomUUID().toString();
		try {
			localFileRepository.fileTransfer(sourceFile, fileName, FileUploadLocation.WEB_SERVER_STATIC_PATH);
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
