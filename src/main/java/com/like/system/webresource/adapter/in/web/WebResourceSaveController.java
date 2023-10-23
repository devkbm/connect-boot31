package com.like.system.webresource.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.webresource.application.port.dto.WebResourceSaveDTO;
import com.like.system.webresource.application.port.in.WebResourceSaveUseCase;

@RestController
public class WebResourceSaveController {

	private WebResourceSaveUseCase useCase;
	
	public WebResourceSaveController(WebResourceSaveUseCase useCase) {
		this.useCase = useCase;		
	}
		
		
	@PostMapping("/api/system/webresource")
	public ResponseEntity<?> saveResource(@RequestBody @Valid WebResourceSaveDTO dto) throws Exception {
																												
		useCase.save(dto);																						
										 					
		return toOne(null, String.format("%d 건 저장되었습니다.", 1));
	}	
}
