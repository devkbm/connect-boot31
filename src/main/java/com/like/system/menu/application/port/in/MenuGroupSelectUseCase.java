package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuGroupQueryDTO;
import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;

public interface MenuGroupSelectUseCase {
	MenuGroupSaveDTO select(String organizationCode, String menuGroupCode);
	
	List<MenuGroupSaveDTO> selectList(MenuGroupQueryDTO dto);
}
