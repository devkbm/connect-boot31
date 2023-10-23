package com.like.system.webresource.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.webresource.application.port.dto.WebResourceQueryDTO;
import com.like.system.webresource.application.port.dto.WebResourceSaveDTO;
import com.like.system.webresource.application.port.in.WebResourceQueryUseCase;
import com.like.system.webresource.domain.WebResourceType;

@RestController
public class WebResourceQueryController {

	private WebResourceQueryUseCase useCase;
	
	public WebResourceQueryController(WebResourceQueryUseCase useCase) {
		this.useCase = useCase; 
	}
	
	@GetMapping("/api/system/webresource")
	public ResponseEntity<?> getWebResourceList(WebResourceQueryDTO condition) {
										
		List<WebResourceSaveDTO> dtoList = useCase.getResourceList(condition);
		
		return toList(dtoList, String.format("%d 건 조회되었습니다.", dtoList.size())); 
	}
	
	@GetMapping("/api/system/webresource/resourcetype")
	public ResponseEntity<?> getWebResourceTypeList() {				
		
		List<HtmlSelectOptionRecord> list = new ArrayList<HtmlSelectOptionRecord>();
		
		for (WebResourceType type : WebResourceType.values()) {			
			list.add(new HtmlSelectOptionRecord(type.getLabel(), type.toString()));
		}
		
		return toList(list, String.format("%d 건 조회되었습니다.", list.size()));
	}
}
