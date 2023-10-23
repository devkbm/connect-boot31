package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuQueryDTO;
import com.like.system.menu.domain.Menu;

public interface MenuSelectDbPort {
	Menu select(String organizationCode, String menuGroupCode, String menuCode);
	
	List<Menu> selectList(MenuQueryDTO dto);
}
