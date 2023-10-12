package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuRoleMappingHierarchyResponseDTO;

public interface MenuRoleHierarchySelectUseCase {

	List<MenuRoleMappingHierarchyResponseDTO> select(String organizationCode, String menuGroupCode, String roleCode);
}
