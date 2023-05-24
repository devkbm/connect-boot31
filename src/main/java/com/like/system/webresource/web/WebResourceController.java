package com.like.system.webresource.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.webresource.boundary.WebResourceDTO;
import com.like.system.webresource.boundary.WebResourceDTO.Form;
import com.like.system.webresource.domain.WebResource;
import com.like.system.webresource.service.WebResourceService;

@RestController
public class WebResourceController {

	private WebResourceService service;
	
	public WebResourceController(WebResourceService menuCommandService) {
		this.service = menuCommandService;		
	}
	
	@GetMapping("/api/system/webresource/{id}")
	public ResponseEntity<?> getResource(@PathVariable String id) {				
		
		WebResource resource = service.getResource(id); 							
		
		WebResourceDTO.Form dto = Form.convertDTO(resource);
		
		return toOne(dto, String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0));
	}
		
	@PostMapping("/api/system/webresource")
	public ResponseEntity<?> saveResource(@RequestBody @Valid WebResourceDTO.Form dto) throws Exception {
																												
		service.saveWebResource(dto);																						
										 					
		return toOne(null, String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/system/webresource/{id}")
	public ResponseEntity<?> delResource(@PathVariable String id) {				
												
		service.deleteWebResource(id);							
		
		return toOne(null, String.format("%d 건 삭제되었습니다.", 1));
	}
}
