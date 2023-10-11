package com.like.system.menu.application.port.out;

import com.like.system.menu.application.port.dto.MenuSaveDTO;

public interface MenuSaveDbPort {
	void save(MenuSaveDTO dto);
}
