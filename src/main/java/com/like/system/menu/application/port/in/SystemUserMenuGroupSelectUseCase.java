package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;

public interface SystemUserMenuGroupSelectUseCase {

	List<MenuGroupSaveDTO> select(String organizationCode, String userId);
}
