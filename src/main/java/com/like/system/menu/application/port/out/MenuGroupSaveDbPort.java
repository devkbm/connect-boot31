package com.like.system.menu.application.port.out;

import com.like.system.menu.domain.MenuGroup;

public interface MenuGroupSaveDbPort {
	void save(MenuGroup entity);
}
