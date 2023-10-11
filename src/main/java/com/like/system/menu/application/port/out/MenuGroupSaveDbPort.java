package com.like.system.menu.application.port.out;

import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;

public interface MenuGroupSaveDbPort {
	void save(MenuGroupSaveDTO dto);
}
