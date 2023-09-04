package com.like.system.menu.application.port.out;

import com.like.system.menu.application.port.in.dto.MenuGroupSaveDTO;

public interface MenuGroupSelectDbPort {
	MenuGroupSaveDTO select(String organizationCode, String menuGroupCode);
}
