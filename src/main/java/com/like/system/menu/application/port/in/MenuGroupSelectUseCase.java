package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.application.port.in.dto.MenuGroupQueryConditionDTO;
import com.like.system.menu.application.port.in.dto.MenuGroupSaveDTO;

public interface MenuGroupSelectUseCase {
	MenuGroupSaveDTO select(String organizationCode, String menuGroupCode);
	
	List<MenuGroupSaveDTO> selectList(MenuGroupQueryConditionDTO dto);
}
