package com.like.system.webresource.application.service;

import org.springframework.stereotype.Service;

import com.like.system.webresource.application.port.dto.WebResourceSaveDTO;
import com.like.system.webresource.application.port.in.WebResourceSaveUseCase;
import com.like.system.webresource.application.port.out.WebResourceCommandDbPort;

@Service
public class WebResourceSaveService implements WebResourceSaveUseCase {

	WebResourceCommandDbPort port;
	
	WebResourceSaveService(WebResourceCommandDbPort port) {
		this.port = port;
	}
	
	@Override
	public void save(WebResourceSaveDTO dto) {
		this.port.save(dto.toEntity());		
	}
}
