package com.like.system.menu.application.port.out;

import com.like.system.menu.application.port.in.dto.MenuSaveDTO;

public interface MenuSelectDbPort {
	MenuSaveDTO select(String organizationCode, String menuGroupCode, String menuCode);
}
