package com.like.system.webresource.application.service;

import org.springframework.stereotype.Service;

import com.like.system.webresource.application.port.in.WebResourceSaveUseCase;
import com.like.system.webresource.application.port.in.dto.WebResourceSaveDTO;
import com.like.system.webresource.application.port.out.WebResourceDbSavePort;

@Service
public class WebResourceSaveService implements WebResourceSaveUseCase {

	WebResourceDbSavePort port;
	
	WebResourceSaveService(WebResourceDbSavePort port) {
		this.port = port;
	}
	
	@Override
	public void save(WebResourceSaveDTO dto) {
		this.port.save(dto);		
	}
}
