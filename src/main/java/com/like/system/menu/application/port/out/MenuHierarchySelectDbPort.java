package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuHierarchyResponseDTO;

public interface MenuHierarchySelectDbPort {
	List<MenuHierarchyResponseDTO> select(String organizationCode, String menuGroupCode);
}
