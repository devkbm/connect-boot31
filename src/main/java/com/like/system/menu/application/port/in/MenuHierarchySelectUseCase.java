package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuHierarchyResponseDTO;

public interface MenuHierarchySelectUseCase {

	List<MenuHierarchyResponseDTO> select(String organizationCode, String menuGroupCode);	
}
