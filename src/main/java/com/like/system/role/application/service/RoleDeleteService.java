package com.like.system.role.application.service;

import org.springframework.stereotype.Service;

import com.like.system.role.application.port.in.RoleDeleteUseCase;
import com.like.system.role.application.port.out.RoleDbDeletePort;

@Service
public class RoleDeleteService implements RoleDeleteUseCase {

	RoleDbDeletePort port;

	public RoleDeleteService(RoleDbDeletePort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, String authorityCode) {
		this.port.delete(organizationCode, authorityCode);		
	}
	
}
