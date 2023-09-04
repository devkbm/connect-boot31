package com.like.system.menu.application.service;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.in.MenuSaveUseCase;
import com.like.system.menu.application.port.in.dto.MenuSaveDTO;
import com.like.system.menu.application.port.out.MenuSaveDbPort;

@Service
public class MenuSaveService implements MenuSaveUseCase {

	MenuSaveDbPort port;
	
	MenuSaveService(MenuSaveDbPort port) {
		this.port = port;
	}
	
	@Override
	public void save(MenuSaveDTO dto) {
		this.port.save(dto);		
	}

}
