package com.like.system.role.application.service;

import org.springframework.stereotype.Service;

import com.like.system.role.application.port.in.RoleSelectUseCase;
import com.like.system.role.application.port.out.RoleDbSelectPort;
import com.like.system.role.domain.Role;

@Service
public class RoleSelectService implements RoleSelectUseCase {

	private RoleDbSelectPort port;
	
	public RoleSelectService(RoleDbSelectPort port) {
		this.port = port;
	}
	
	@Override
	public Role select(String organizationCode, String authorityCode) { 
		return port.find(organizationCode, authorityCode);
	}

}
