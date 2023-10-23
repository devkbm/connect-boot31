package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.dto.MenuHierarchyResponseDTO;
import com.like.system.menu.application.port.in.SystemUserMenuHierarchySelectUseCase;
import com.like.system.menu.application.port.out.SystemUserMenuHierarchySelectDbPort;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;

@Service
public class SystemUserMenuHierarchySelectService implements SystemUserMenuHierarchySelectUseCase {
	
	SystemUserMenuHierarchySelectDbPort dbPort;
	SystemUserSelectUseCase userSelectUseCase;
	
	SystemUserMenuHierarchySelectService(SystemUserMenuHierarchySelectDbPort dbPort,
										 SystemUserSelectUseCase userSelectUseCase) {
		this.dbPort = dbPort;
		this.userSelectUseCase = userSelectUseCase;
	}
		
	@Override
	public List<MenuHierarchyResponseDTO> select(String organizationCode, String userId, String menuGroupCode) {
		SystemUserSaveDTO userDTO = userSelectUseCase.selectDTO(organizationCode, userId);
					
		return this.dbPort.select(organizationCode, menuGroupCode, userDTO.roleList());
	}

}
