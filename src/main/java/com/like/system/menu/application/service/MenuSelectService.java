package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.dto.MenuQueryDTO;
import com.like.system.menu.application.port.dto.MenuSaveDTO;
import com.like.system.menu.application.port.in.MenuSelectUseCase;
import com.like.system.menu.application.port.out.MenuSelectDbPort;

@Service
public class MenuSelectService implements MenuSelectUseCase {

	MenuSelectDbPort port;
	
	MenuSelectService(MenuSelectDbPort port) {
		this.port = port;
	}
	
	@Override
	public MenuSaveDTO select(String organizationCode, String menuGroupCode, String menuCode) {
		return MenuSaveDTO.toDTO(this.port.select(organizationCode, menuGroupCode, menuCode));
	}

	@Override
	public List<MenuSaveDTO> selectList(MenuQueryDTO dto) {
		return this.port.selectList(dto)
						.stream()
						.map(e -> MenuSaveDTO.toDTO(e))
						.toList();
	}

}
