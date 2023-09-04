package com.like.system.menu.application.port.in;

import com.like.system.menu.application.port.in.dto.MenuGroupSaveDTO;

public interface MenuGroupSelectUseCase {
	MenuGroupSaveDTO select(String organizationCode, String menuGroupCode);
}
