package com.like.system.user.application.service;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.in.SystemUserDeleteUseCase;
import com.like.system.user.application.port.out.SystemUserDbDeletePort;

@Service
public class SystemUserDeleteService implements SystemUserDeleteUseCase {

	SystemUserDbDeletePort port;
	
	SystemUserDeleteService(SystemUserDbDeletePort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, String userId) {
		this.port.delete(organizationCode, userId);		
	}

}
