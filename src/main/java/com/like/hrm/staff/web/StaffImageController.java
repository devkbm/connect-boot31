package com.like.hrm.staff.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.service.StaffService;
import com.like.system.file.adapter.out.file.FileUploadLocation;
import com.like.system.file.application.port.in.WebServerDownloadUseCase;
import com.like.system.file.application.service.FileService;

@Controller
public class StaffImageController {
	
	private StaffService service;	
	private WebServerDownloadUseCase fileDownloadUseCase;
	FileService fileService;
				
	public StaffImageController(StaffService service
							   ,WebServerDownloadUseCase fileDownloadUseCase
							   ,FileService fileService) {
		this.service = service;
		this.fileDownloadUseCase = fileDownloadUseCase;
		this.fileService = fileService;
	}

	@PostMapping("/api/hrm/staff/changeimage")
	public ResponseEntity<?> changeEmployeeImage(@RequestPart MultipartFile file
												,String organizationCode
												,String staffNo) throws Exception {				
		
		Map<String, Object> response = new HashMap<>();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);				
		
		String uuid = UUID.randomUUID().toString();
		String path = fileService.fileTransefer(file, uuid, FileUploadLocation.WEB_SERVER_STATIC_PATH);
		
		Staff staff = service.getStaff(organizationCode, staffNo);
				
		staff.changeImagePath(uuid);
		
		service.saveStaff(staff);
		
		response.put("data", path);
		response.put("status", "done");
							
		return new ResponseEntity<Map<String,Object>>(response, responseHeaders, HttpStatus.OK);
	}
		
	@GetMapping("/api/hrm/staff/downloadimage")
	public HttpServletResponse downloadStaffImage(HttpServletResponse response
												 ,@RequestParam String organizationCode
												 ,@RequestParam String staffId) throws Exception {
				
		Staff staff = service.getStaff(organizationCode, staffId);			
		
		/*
		File file = fileDownloadUseCase.getWebStaticFilePath(staff.getImagePath());
				
		response = this.setResponse(response, file.length(), staffId);
		
		fileDownloadUseCase.downloadFile(file, response);
		*/
		
		fileDownloadUseCase.downloadWebStaticPath(staff.getImagePath(), staffId, response);
			
		return response;
	}
	
}
