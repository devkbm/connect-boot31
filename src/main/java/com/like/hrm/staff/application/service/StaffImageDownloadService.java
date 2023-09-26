package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.in.StaffImageDownloadUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.system.file.application.port.in.FileServerDownloadUseCase;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class StaffImageDownloadService implements StaffImageDownloadUseCase {

	StaffCommandDbPort dbPort;
	FileServerDownloadUseCase downloadUseCase;
	
	StaffImageDownloadService(StaffCommandDbPort dbPort
							 ,FileServerDownloadUseCase downloadUseCase) {
		this.dbPort = dbPort;
		this.downloadUseCase = downloadUseCase;
	}
	
	@Override
	public HttpServletResponse downloadImageFile(String organizationCode, String staffNo, HttpServletResponse response) throws Exception {
		Staff entity = this.dbPort.select(organizationCode, staffNo);
				
		downloadUseCase.download(entity.getImagePath(), response);
		
		return response;
	}

}
