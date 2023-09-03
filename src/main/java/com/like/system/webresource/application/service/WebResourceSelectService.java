package com.like.system.webresource.application.service;

import org.springframework.stereotype.Service;

import com.like.system.webresource.application.port.in.WebResourceSelectUseCase;
import com.like.system.webresource.application.port.in.dto.WebResourceSaveDTO;
import com.like.system.webresource.application.port.out.WebResourceDbSelectPort;

@Service
public class WebResourceSelectService implements WebResourceSelectUseCase {

	WebResourceDbSelectPort port;
	
	WebResourceSelectService(WebResourceDbSelectPort port) {
		this.port = port;
	}
	
	@Override
	public WebResourceSaveDTO select(String webResourceId) {
		return this.port.select(webResourceId);
	}

}
