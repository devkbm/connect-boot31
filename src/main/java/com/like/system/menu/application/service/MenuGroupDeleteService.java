package com.like.system.menu.application.service;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.in.MenuGroupDeleteUseCase;
import com.like.system.menu.application.port.out.MenuGroupDeleteDbPort;

@Service
public class MenuGroupDeleteService implements MenuGroupDeleteUseCase {

	MenuGroupDeleteDbPort port;
	
	MenuGroupDeleteService(MenuGroupDeleteDbPort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, String menuGroupCode) {
		this.port.delete(organizationCode, menuGroupCode);
	}

}
