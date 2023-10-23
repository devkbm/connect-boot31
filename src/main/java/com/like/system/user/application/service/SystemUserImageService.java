package com.like.system.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.application.port.in.FileServerDownloadUseCase;
import com.like.system.user.application.port.in.SystemUserImageChangeUseCase;
import com.like.system.user.application.port.in.SystemUserImageFileUseCase;
import com.like.system.user.application.port.out.SystemUserSaveDbPort;
import com.like.system.user.application.port.out.SystemUserSelectDbPort;
import com.like.system.user.domain.ProfilePictureRepository;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class SystemUserImageService implements SystemUserImageFileUseCase, SystemUserImageChangeUseCase {

	SystemUserSelectDbPort port;
	SystemUserSaveDbPort savePort;
	ProfilePictureRepository profilePictureRepository;
	FileServerDownloadUseCase fileDownLoadUseCase;
		
	SystemUserImageService(SystemUserSelectDbPort port
						  ,SystemUserSaveDbPort savePort
						  ,ProfilePictureRepository profilePictureRepository
						  ,FileServerDownloadUseCase fileDownLoadUseCase) {
		this.port = port;
		this.savePort = savePort;
		this.profilePictureRepository = profilePictureRepository;
		this.fileDownLoadUseCase = fileDownLoadUseCase;
	}
	
	@Override
	public HttpServletResponse downloadImageFile(String organizationCode, String userId, HttpServletResponse response) throws Exception {
		SystemUser user = this.port.select(organizationCode, userId);
				
		fileDownLoadUseCase.download(user.getImage(), response);
		
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

}
