package com.like.system.menu.domain;

import java.util.List;

import com.like.system.menu.application.port.in.dto.MenuDTO;
import com.like.system.menu.application.port.in.dto.MenuGroupDTO;

public interface MenuQueryRepository {

	List<MenuGroup> getMenuGroupList(MenuGroupDTO.Search condition);
	
	List<MenuGroup> getMenuGroupList(String likeMenuGroupName);
	
	List<Menu> getMenuList(MenuDTO.Search condition);
	
}
