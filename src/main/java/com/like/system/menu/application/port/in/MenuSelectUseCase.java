package com.like.system.menu.application.port.in;

import com.like.system.menu.application.port.in.dto.MenuSaveDTO;

public interface MenuSelectUseCase {
	MenuSaveDTO select(String organizationCode, String menuGroupCode, String menuCode);
}
