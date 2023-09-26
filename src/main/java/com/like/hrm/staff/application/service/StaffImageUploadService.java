package com.like.hrm.staff.application.service;

import org.springframework.web.multipart.MultipartFile;

import com.like.hrm.staff.application.port.in.StaffImageUploadUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.system.file.application.port.in.FileServerUploadUseCase;

public class StaffImageUploadService implements StaffImageUploadUseCase {
	
	StaffCommandDbPort dbPort;
	FileServerUploadUseCase uploadUseCase;
	
	StaffImageUploadService(StaffCommandDbPort dbPort
						   ,FileServerUploadUseCase uploadUseCase) {
		this.dbPort = dbPort;
		this.uploadUseCase = uploadUseCase;
	}
	
	@Override
	public String upload(String organizationCode, String staffNo, MultipartFile file) {
		
		Staff entity = this.dbPort.select(organizationCode, staffNo);
		
		if (entity == null) return null;
		
		String path = uploadUseCase.uploadFile(file, "kbm", "SystemUser").getId().toString();
		
		entity.changeImagePath(path);			
		
		this.dbPort.save(entity);
		
		return path;
	}

}
