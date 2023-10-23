package com.like.system.user.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.like.system.dept.application.port.out.DeptSelectPort;
import com.like.system.dept.domain.Dept;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.in.SystemUserSaveUseCase;
import com.like.system.user.application.port.out.SystemUserSaveDbPort;
import com.like.system.user.application.port.out.SystemUserRoleSaveDbPort;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserRole;

@Transactional
@Service
public class SystemUserSaveService implements SystemUserSaveUseCase {

	SystemUserSaveDbPort dbPort;	
	DeptSelectPort deptDbPort;
	SystemUserRoleSaveDbPort userRoleDbPort;
	
	SystemUserSaveService(SystemUserSaveDbPort dbPort,
						  DeptSelectPort deptDbPort,
						  SystemUserRoleSaveDbPort userRoleDbPort) {
		this.dbPort = dbPort;
		this.deptDbPort = deptDbPort;
		this.userRoleDbPort = userRoleDbPort;
	}
	
	@Override
	public void save(SystemUserSaveDTO dto) {
		Dept dept = StringUtils.hasText(dto.deptCode()) ? deptDbPort.select(dto.organizationCode(), dto.deptCode()) : null;
		SystemUser user = dto.newUser(dept);
				
		this.dbPort.save(user);
		
		List<SystemUserRole> roles = this.toSystemUserRole(dto, user);
		
		this.userRoleDbPort.save(roles);
	}
	
	private List<SystemUserRole> toSystemUserRole(SystemUserSaveDTO dto, SystemUser user) {
		return this.userRoleDbPort.select(dto.organizationCode(), dto.roleList())
								  .stream()
								  .map(e -> new SystemUserRole(user, e))
								  .toList();
	}

}
