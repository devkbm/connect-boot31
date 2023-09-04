package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.in.MenuGroupSelectUseCase;
import com.like.system.menu.application.port.in.dto.MenuGroupQueryConditionDTO;
import com.like.system.menu.application.port.in.dto.MenuGroupSaveDTO;
import com.like.system.menu.application.port.out.MenuGroupSelectDbPort;

@Service
public class MenuGroupSelectService implements MenuGroupSelectUseCase {

	MenuGroupSelectDbPort port;
	
	MenuGroupSelectService(MenuGroupSelectDbPort port) {
		this.port = port;
	}
	
	@Override
	public MenuGroupSaveDTO select(String organizationCode, String menuGroupCode) {
		return this.port.select(organizationCode, menuGroupCode);
	}

	@Override
	public List<MenuGroupSaveDTO> selectList(MenuGroupQueryConditionDTO dto) {
		return this.port.selectList(dto);
	}

}
