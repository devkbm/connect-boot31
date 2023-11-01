package com.like.system.role.application.service;

import org.springframework.stereotype.Service;

import com.like.system.role.application.port.in.RoleSelectUseCase;
import com.like.system.role.application.port.out.RoleCommandDbPort;
import com.like.system.role.domain.Role;

@Service
public class RoleSelectService implements RoleSelectUseCase {

	private RoleCommandDbPort port;
	
	public RoleSelectService(RoleCommandDbPort port) {
		this.port = port;
	}
	
	@Override
	public Role select(String organizationCode, String roleCode) { 
		return port.find(organizationCode, roleCode);
	}

}
