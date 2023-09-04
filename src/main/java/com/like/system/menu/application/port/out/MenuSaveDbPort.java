package com.like.system.menu.application.port.out;

import com.like.system.menu.application.port.in.dto.MenuSaveDTO;

public interface MenuSaveDbPort {
	void save(MenuSaveDTO dto);
}
