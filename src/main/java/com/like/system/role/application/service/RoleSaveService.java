package com.like.system.role.application.service;

import org.springframework.stereotype.Service;

import com.like.system.role.application.port.dto.RoleSaveDTO;
import com.like.system.role.application.port.in.RoleSaveUseCase;
import com.like.system.role.application.port.out.RoleDbSavePort;
import com.like.system.role.application.port.out.RoleDbSelectPort;
import com.like.system.role.domain.Role;

@Service
public class RoleSaveService implements RoleSaveUseCase {

	RoleDbSelectPort selectPort;
	RoleDbSavePort savePort;
	
	public RoleSaveService(RoleDbSelectPort selectPort, RoleDbSavePort savePort) {
		this.selectPort = selectPort;
		this.savePort = savePort;
	}

	@Override
	public void save(RoleSaveDTO dto) {
		Role authority = selectPort.find(dto.organizationCode(), dto.roleCode());			
		
		if (authority == null) {
			authority = dto.newEntity();
		} else {
			dto.modifyEntity(authority);
		}
		
		savePort.save(authority);		
	}
	
}
