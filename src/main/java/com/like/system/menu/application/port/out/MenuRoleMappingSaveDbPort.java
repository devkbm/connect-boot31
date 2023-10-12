package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuRoleMappingSaveDTO;

public interface MenuRoleMappingSaveDbPort {

	void save(List<MenuRoleMappingSaveDTO> entityList);
}
