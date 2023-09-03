package com.like.system.webresource.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.webresource.application.port.in.WebResourceQueryUseCase;
import com.like.system.webresource.application.port.in.dto.WebResourceQueryConditionDTO;
import com.like.system.webresource.application.port.in.dto.WebResourceSaveDTO;
import com.like.system.webresource.application.port.out.WebResourceQueryDbPort;

@Service
public class WebResourceQueryService implements WebResourceQueryUseCase {

	private WebResourceQueryDbPort port;
	
	public WebResourceQueryService(WebResourceQueryDbPort port) {
		this.port = port;		
	}	

	@Override
	public List<WebResourceSaveDTO> getResourceList(WebResourceQueryConditionDTO dto) {	
		return this.port.getResourceList(dto);
	}
}
