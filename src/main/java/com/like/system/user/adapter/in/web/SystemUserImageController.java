package com.like.system.user.adapter.in.web;

import java.util.HashMap;
import java.util.Map;

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

import com.like.system.user.application.port.in.SystemUserImageChangeUseCase;
import com.like.system.user.application.port.in.SystemUserImageFileUseCase;

@Controller
public class SystemUserImageController {
			
	SystemUserImageFileUseCase imageSelectUseCase;
	SystemUserImageChangeUseCase imageChangeUseCase;	
		
	public SystemUserImageController(SystemUserImageFileUseCase imageSelectUseCase
									,SystemUserImageChangeUseCase imageChangeUseCase) {
		this.imageSelectUseCase = imageSelectUseCase;
		this.imageChangeUseCase = imageChangeUseCase;		
	}
	
	@GetMapping("/api/system/user/image")
	public HttpServletResponse downloadUserImage(HttpServletResponse response
												,@RequestParam String organizationCode
											    ,@RequestParam String userId) throws Exception {									
		
		return imageSelectUseCase.downloadImageFile(organizationCode, userId, response);
	}
	
	@PostMapping("/api/system/user/image")
	public ResponseEntity<?> changeUserImage(@RequestPart MultipartFile file
											,@RequestParam String organizationCode	
											,String userId) throws Exception {				
												
		String fileName = imageChangeUseCase.changeImage(organizationCode, userId, file);			
							
		return new ResponseEntity<Map<String,Object>>(setUploadResponseBody(fileName), setUploadResponseHeader(), HttpStatus.OK);
	}	
	
	private Map<String, Object> setUploadResponseBody(String fileName) {		
		Map<String, Object> response = new HashMap<>();
		response.put("data", fileName);
		response.put("status", "done");
				
		return response;
	}
	
	private HttpHeaders setUploadResponseHeader() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		return responseHeaders;		
	}
}
