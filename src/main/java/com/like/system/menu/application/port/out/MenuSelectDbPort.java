package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuQueryDTO;
import com.like.system.menu.application.port.dto.MenuSaveDTO;

public interface MenuSelectDbPort {
	MenuSaveDTO select(String organizationCode, String menuGroupCode, String menuCode);
	
	List<MenuSaveDTO> selectList(MenuQueryDTO dto);
}
