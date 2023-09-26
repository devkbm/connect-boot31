package com.like.hrm.staff.adapter.in.web;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.like.hrm.staff.application.port.in.StaffImageDownloadUseCase;

@Controller
public class StaffImageDownloadController {
		
	StaffImageDownloadUseCase useCase;
				
	public StaffImageDownloadController(StaffImageDownloadUseCase useCase) {		
		this.useCase = useCase;
	}
	
	@GetMapping("/api/hrm/staff/downloadimage")
	public HttpServletResponse downloadStaffImage(HttpServletResponse response
												 ,@RequestParam String organizationCode
												 ,@RequestParam String staffId) throws Exception {
		
		this.useCase.downloadImageFile(organizationCode, staffId, response);
				
		return response;
	}
	
}
