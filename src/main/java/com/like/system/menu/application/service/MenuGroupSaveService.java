package com.like.system.menu.application.service;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;
import com.like.system.menu.application.port.in.MenuGroupSaveUseCase;
import com.like.system.menu.application.port.out.MenuGroupSaveDbPort;

@Service
public class MenuGroupSaveService implements MenuGroupSaveUseCase {

	MenuGroupSaveDbPort port;
	
	MenuGroupSaveService(MenuGroupSaveDbPort port) {
		this.port = port;
	}
	
	@Override
	public void save(MenuGroupSaveDTO dto) {
		this.port.save(dto.newMenuGroup());		
	}

}
