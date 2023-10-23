package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;
import com.like.system.menu.application.port.in.SystemUserMenuGroupSelectUseCase;
import com.like.system.menu.application.port.out.SystemUserMenuGroupSelectDbPort;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;

@Service
public class SystemUserMenuGroupSelectService implements SystemUserMenuGroupSelectUseCase {

	SystemUserMenuGroupSelectDbPort dbPort;
	SystemUserSelectUseCase userSelectUseCase;
	
	SystemUserMenuGroupSelectService(SystemUserMenuGroupSelectDbPort dbPort,
									 SystemUserSelectUseCase userSelectUseCase) {
		this.dbPort = dbPort;
		this.userSelectUseCase = userSelectUseCase;
	}
	
	@Override
	public List<MenuGroupSaveDTO> select(String organizationCode, String userId) {
		SystemUserSaveDTO userDTO = userSelectUseCase.selectDTO(organizationCode, userId);
		
		return this.dbPort.select(organizationCode, userDTO.roleList())
						  .stream()
						  .map(e -> MenuGroupSaveDTO.toDTO(e))
						  .toList();
	}

}
