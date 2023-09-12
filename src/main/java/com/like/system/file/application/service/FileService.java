package com.like.system.file.application.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.adapter.out.file.FileConverterUtil;
import com.like.system.file.adapter.out.file.FileServerRepository;
import com.like.system.file.adapter.out.file.FileServerRepository.FileUploadLocation;
import com.like.system.file.adapter.out.persistence.jpa.repository.FileInfoJpaRepository;
import com.like.system.file.application.port.out.FileInfoCommandDbPort;
import com.like.system.file.domain.FileInfo;

@Service
public class FileService {
			
	private FileInfoJpaRepository fileInfoRepository;			
	private FileServerRepository localFileRepository;
	FileInfoCommandDbPort port;
	
	public FileService(FileInfoJpaRepository fileInfoRepository
			          ,FileServerRepository localFileRepository
			          ,FileInfoCommandDbPort port) {
		this.fileInfoRepository = fileInfoRepository;
		this.localFileRepository = localFileRepository;
		this.port = port;
	}
	
	@Transactional
	public FileInfo uploadFile(MultipartFile sourceFile, String userId, String pgmId) throws FileNotFoundException, IOException {
									
		String uuid = UUID.randomUUID().toString();
		
		fileTransefer(sourceFile, uuid, FileUploadLocation.FILE_SERVER_PATH);
		
		FileInfo entity = FileInfo.create(sourceFile, localFileRepository.getFileServerUploadPath(), uuid, userId, pgmId);		
												
		return this.port.save(entity);		
	}
	
	@Transactional
	public List<FileInfo> uploadFile(List<MultipartFile> sourceFiles, String userId, String pgmId) throws FileNotFoundException, IOException {
		
		List<FileInfo> rtn = new ArrayList<FileInfo>();
		
		for (MultipartFile multipartFile : sourceFiles) {			
										
			String uuid = UUID.randomUUID().toString();
			
			fileTransefer(multipartFile, uuid, FileUploadLocation.FILE_SERVER_PATH);
			
			FileInfo file = FileInfo.create(multipartFile, localFileRepository.getFileServerUploadPath(), uuid, userId, pgmId);	
			
			rtn.add(fileInfoRepository.save(file));
		}
												
		return rtn; 		
	}
			
	@Transactional
	public void deleteFile(FileInfo fileInfo) throws FileNotFoundException {
		
		localFileRepository.deleteFile(new File(fileInfo.getPath(), fileInfo.getUuid()));
		
		//fileInfoRepository.delete(fileInfo);
		this.port.delete(fileInfo.getId().toString());
	}
	
	public void deleteStaticFile(String fileName) throws FileNotFoundException {
		localFileRepository.deleteStaticFile(fileName);
	}
	
	public FileInfo getFileInfo(String id) {
		UUID uuid = UUID.fromString(id);
		return fileInfoRepository.findById(uuid).orElse(null);
	}
	
	public List<FileInfo> getFileInfoList(List<String> id) {
		List<UUID> uuids = id.stream().map(e -> UUID.fromString(e)).toList();
		return fileInfoRepository.findAllById(uuids);
	}
	
	public String fileTransefer(MultipartFile sourceFile, String fileName, FileUploadLocation location) throws FileNotFoundException, IOException {
		return localFileRepository.fileTransfer(sourceFile, fileName, location);
	}
	
	public String downloadBase64(String id) throws FileNotFoundException, IOException {
		UUID uid = UUID.fromString(id);
		FileInfo info = fileInfoRepository.findById(uid).orElse(null);					
		File file = info.getFile();
		
		return FileConverterUtil.getBase64String(file);		
	}
	
	public File getStaticPathFile(String fileName) {
		return localFileRepository.getStaticPathFile(fileName);
	}
		
	
}
