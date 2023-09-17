package com.like.system.file.application.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.adapter.out.file.FileServerRepository;
import com.like.system.file.adapter.out.file.FileUploadLocation;
import com.like.system.file.application.port.in.FileUploadUseCase;
import com.like.system.file.application.port.out.FileInfoCommandDbPort;
import com.like.system.file.domain.FileInfo;

@Service
public class FileUploadService implements FileUploadUseCase {

	FileInfoCommandDbPort port;
	FileServerRepository localFileRepository;
	
	FileUploadService(FileInfoCommandDbPort port
					 ,FileServerRepository localFileRepository) {
		this.port = port;
		this.localFileRepository = localFileRepository;
	}
	
	@Override
	public FileInfo uploadFile(MultipartFile sourceFile, String userId, String pgmId) {
		String uuid = UUID.randomUUID().toString();
				
		try {
			localFileRepository.fileTransfer(sourceFile, uuid, FileUploadLocation.FILE_SERVER_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileInfo entity = FileInfo.create(sourceFile, localFileRepository.getFileServerUploadPath(), uuid, userId, pgmId);		
												
		return this.port.save(entity);	
		
	}

}
