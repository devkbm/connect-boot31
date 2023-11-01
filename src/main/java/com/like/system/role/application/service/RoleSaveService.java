package com.like.system.role.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.role.application.port.dto.RoleSaveDTO;
import com.like.system.role.application.port.in.RoleSaveUseCase;
import com.like.system.role.application.port.out.RoleCommandDbPort;
import com.like.system.role.domain.Role;

@Transactional
@Service
public class RoleSaveService implements RoleSaveUseCase {

	RoleCommandDbPort dbPort;	
	
	public RoleSaveService(RoleCommandDbPort dbPort) {
		this.dbPort = dbPort;		
	}

	@Override
	public void save(RoleSaveDTO dto) {
		Role authority = dbPort.find(dto.organizationCode(), dto.roleCode());			
		
		if (authority == null) {
			authority = dto.newEntity();
		} else {
			dto.modifyEntity(authority);
		}
		
		dbPort.save(authority);		
	}
	
}
