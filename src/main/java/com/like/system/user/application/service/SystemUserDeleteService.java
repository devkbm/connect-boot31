package com.like.system.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.user.application.port.in.SystemUserDeleteUseCase;
import com.like.system.user.application.port.out.SystemUserCommandDbPort;

@Transactional
@Service
public class SystemUserDeleteService implements SystemUserDeleteUseCase {

	SystemUserCommandDbPort port;
	
	SystemUserDeleteService(SystemUserCommandDbPort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, String userId) {
		this.port.delete(organizationCode, userId);		
	}

}
