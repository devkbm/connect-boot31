package com.like.system.user.application.service;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;
import com.like.system.user.application.port.out.SystemUserCommandDbPort;

@Service
public class SystemUserSelectService implements SystemUserSelectUseCase {

	SystemUserCommandDbPort dbPort;
	
	SystemUserSelectService(SystemUserCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public SystemUserSaveDTO selectDTO(String organizationCode, String userId) {
		return SystemUserSaveDTO.toDTO(this.dbPort.select(organizationCode, userId));
	}
}
