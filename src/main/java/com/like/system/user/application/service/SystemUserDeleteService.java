package com.like.system.user.application.service;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.in.SystemUserDeleteUseCase;
import com.like.system.user.application.port.out.SystemUserDeleteDbPort;

@Service
public class SystemUserDeleteService implements SystemUserDeleteUseCase {

	SystemUserDeleteDbPort port;
	
	SystemUserDeleteService(SystemUserDeleteDbPort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, String userId) {
		this.port.delete(organizationCode, userId);		
	}

}
