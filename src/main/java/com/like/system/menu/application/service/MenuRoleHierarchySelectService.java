package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.dto.MenuRoleMappingHierarchyResponseDTO;
import com.like.system.menu.application.port.in.MenuRoleHierarchySelectUseCase;
import com.like.system.menu.application.port.out.MenuRoleHierarchySelectDbPort;

@Service
public class MenuRoleHierarchySelectService implements MenuRoleHierarchySelectUseCase {

	MenuRoleHierarchySelectDbPort dbPort;
	
	MenuRoleHierarchySelectService(MenuRoleHierarchySelectDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<MenuRoleMappingHierarchyResponseDTO> select(
			String organizationCode, 
			String menuGroupCode,
			String roleCode) {
		
		return this.dbPort.select(organizationCode, menuGroupCode, roleCode);
	}

}
