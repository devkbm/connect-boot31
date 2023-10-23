package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.domain.MenuGroup;

public interface SystemUserMenuGroupSelectDbPort {
	List<MenuGroup> select(String organizationCode, List<String> roleCodes);
}
