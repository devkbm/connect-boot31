package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.domain.MenuRoleMapping;

public interface MenuRoleMappingSaveUseCase {

	void save(List<MenuRoleMapping> entityList);
}
