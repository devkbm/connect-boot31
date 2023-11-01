package com.like.system.role.application.service;

import org.springframework.stereotype.Service;

import com.like.system.role.application.port.in.RoleDeleteUseCase;
import com.like.system.role.application.port.out.RoleCommandDbPort;

@Service
public class RoleDeleteService implements RoleDeleteUseCase {

	RoleCommandDbPort port;

	public RoleDeleteService(RoleCommandDbPort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, String roleCode) {
		this.port.delete(organizationCode, roleCode);		
	}
	
}
