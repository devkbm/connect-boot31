package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.dto.MenuRoleMappingSaveDTO;
import com.like.system.menu.application.port.in.MenuRoleMappingSaveUseCase;
import com.like.system.menu.application.port.out.MenuRoleMappingSaveDbPort;

@Service
public class MenuRoleMappingSaveService implements MenuRoleMappingSaveUseCase {

	MenuRoleMappingSaveDbPort dbPort;
	
	MenuRoleMappingSaveService(MenuRoleMappingSaveDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void save(List<MenuRoleMappingSaveDTO> entityList) {
		this.dbPort.save(entityList);		
	}

}
