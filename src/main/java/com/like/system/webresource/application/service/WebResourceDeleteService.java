package com.like.system.webresource.application.service;

import org.springframework.stereotype.Service;

import com.like.system.webresource.application.port.in.WebResourceDeleteUseCase;
import com.like.system.webresource.application.port.out.WebResourceDbDeletePort;

@Service
public class WebResourceDeleteService implements WebResourceDeleteUseCase {

	WebResourceDbDeletePort port;
	
	WebResourceDeleteService(WebResourceDbDeletePort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String webResourceId) {
		this.port.delete(webResourceId);		
	}
	
}
