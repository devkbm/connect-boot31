package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.application.port.in.dto.MenuQueryConditionDTO;
import com.like.system.menu.application.port.in.dto.MenuSaveDTO;

public interface MenuSelectUseCase {
	MenuSaveDTO select(String organizationCode, String menuGroupCode, String menuCode);
	
	List<MenuSaveDTO> selectList(MenuQueryConditionDTO dto);
}
