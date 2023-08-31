package com.like.system.user.application.service;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.in.SystemUserSaveUseCase;
import com.like.system.user.application.port.in.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.out.SystemUserSavePort;

@Service
public class SystemUserSaveService implements SystemUserSaveUseCase {

	SystemUserSavePort port;
	
	SystemUserSaveService(SystemUserSavePort port) {
		this.port = port;
	}
	
	@Override
	public void save(SystemUserSaveDTO dto) {
		this.port.save(dto);		
	}
	
	

}
