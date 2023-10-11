package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.dto.MenuHierarchyResponseDTO;
import com.like.system.menu.application.port.in.MenuHierarchySelectUseCase;
import com.like.system.menu.application.port.out.MenuHierarchySelectDbPort;

@Service
public class MenuHierarchySelectService implements MenuHierarchySelectUseCase {

	MenuHierarchySelectDbPort port;
	
	MenuHierarchySelectService(MenuHierarchySelectDbPort port) {
		this.port = port;
	}
	@Override
	public List<MenuHierarchyResponseDTO> select(String organizationCode, String menuGroupCode) {
		return this.port.select(organizationCode, menuGroupCode);
	}
	
	

}
