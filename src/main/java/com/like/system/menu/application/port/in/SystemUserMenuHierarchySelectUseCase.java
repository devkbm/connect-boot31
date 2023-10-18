package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuHierarchyResponseDTO;

public interface SystemUserMenuHierarchySelectUseCase {
	List<MenuHierarchyResponseDTO> select(String organizationCode, String userId, String menuGroupCode);
}
