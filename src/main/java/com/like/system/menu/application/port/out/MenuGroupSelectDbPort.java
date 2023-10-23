package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuGroupQueryDTO;
import com.like.system.menu.domain.MenuGroup;

public interface MenuGroupSelectDbPort {
	MenuGroup select(String organizationCode, String menuGroupCode);
	
	List<MenuGroup> selectList(MenuGroupQueryDTO dto);
}
