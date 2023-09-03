package com.like.system.user.application.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.application.service.FileService;
import com.like.system.user.application.port.in.SystemUserImageChangeUseCase;
import com.like.system.user.application.port.in.SystemUserImageFileUseCase;
import com.like.system.user.application.port.out.SystemUserDbSavePort;
import com.like.system.user.application.port.out.SystemUserDbSelectPort;
import com.like.system.user.domain.ProfilePictureRepository;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class SystemUserImageService implements SystemUserImageFileUseCase, SystemUserImageChangeUseCase {

	SystemUserDbSelectPort port;
	SystemUserDbSavePort savePort;
	ProfilePictureRepository profilePictureRepository;
	FileService fileService;
	
	
	SystemUserImageService(SystemUserDbSelectPort port
						  ,SystemUserDbSavePort savePort
						  ,ProfilePictureRepository profilePictureRepository
						  ,FileService fileService) {
		this.port = port;
		this.savePort = savePort;
		this.profilePictureRepository = profilePictureRepository;
		this.fileService = fileService;
	}
	
	@Override
	public HttpServletResponse downloadImageFile(String organizationCode, String userId, HttpServletResponse response) throws Exception {
		SystemUser user = this.port.select(organizationCode, userId);
		File file = fileService.getStaticPathFile(user.getImage()); 
		
		response = setDownloadResponseHeader(response, userId, file.length());
		
		fileService.downloadFile(file, response);
		
		return response;
	}

	@Override
	public String changeImage(String organizationCode, String userId, MultipartFile file) {
		SystemUser user = this.port.select(organizationCode, userId);
		
		if (user == null) return null;
		
		String path = user.changeImage(profilePictureRepository, file);
		
		this.savePort.save(user);
		
		return path;
	}
	
	private HttpServletResponse setDownloadResponseHeader(HttpServletResponse response, String fileName, long fileSize) throws Exception {
		
		// get MIME type of the file
		String mimeType= null;
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";	         
		}
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLengthLong(fileSize);
		response.setCharacterEncoding("UTF-8");
		
		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment;filename=\"%s\"", new String(fileName.getBytes("UTF-8"), "8859_1"));
		
		response.setHeader(headerKey, headerValue);
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");	
		
		return response;
	}

}
