package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.domain.MenuRoleMapping;

public interface MenuRoleMappingSaveDbPort {

	void clear(String orgnizationCode, String menuGroupCode, String roleCode);
	
	void save(List<MenuRoleMapping> entityList);
}
