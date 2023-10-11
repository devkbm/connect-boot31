package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuGroupQueryConditionDTO;
import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;

public interface MenuGroupSelectDbPort {
	MenuGroupSaveDTO select(String organizationCode, String menuGroupCode);
	
	List<MenuGroupSaveDTO> selectList(MenuGroupQueryConditionDTO dto);
}
