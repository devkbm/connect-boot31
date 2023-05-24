package com.like.system.menu.domain;

import java.util.List;

import com.like.system.menu.boundary.MenuDTO;
import com.like.system.menu.boundary.MenuGroupDTO;

public interface MenuQueryRepository {

	List<MenuGroup> getMenuGroupList(MenuGroupDTO.Search condition);
	
	List<MenuGroup> getMenuGroupList(String likeMenuGroupName);
	
	List<Menu> getMenuList(MenuDTO.Search condition);
	
}
