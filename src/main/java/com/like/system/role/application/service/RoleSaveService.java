package com.like.system.role.application.service;

import org.springframework.stereotype.Service;

import com.like.system.role.application.port.dto.RoleSaveDTO;
import com.like.system.role.application.port.in.RoleSaveUseCase;
import com.like.system.role.application.port.out.RoleSaveDbPort;
import com.like.system.role.application.port.out.RoleSelectDbPort;
import com.like.system.role.domain.Role;

@Service
public class RoleSaveService implements RoleSaveUseCase {

	RoleSelectDbPort selectPort;
	RoleSaveDbPort savePort;
	
	public RoleSaveService(RoleSelectDbPort selectPort, RoleSaveDbPort savePort) {
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
