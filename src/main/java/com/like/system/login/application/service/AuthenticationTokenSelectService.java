package com.like.system.login.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.login.application.port.in.AuthenticationTokenSelectUseCase;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;
import com.like.system.menu.application.port.in.SystemUserMenuGroupSelectUseCase;
import com.like.system.user.application.port.in.share.SystemUserCommonSelectUseCase;
import com.like.system.user.domain.SystemUser;

@Transactional
@Service
public class AuthenticationTokenSelectService implements AuthenticationTokenSelectUseCase {

	SystemUserCommonSelectUseCase userSelectUseCase;
	SystemUserMenuGroupSelectUseCase menuGroupSelectUseCase;
	
	AuthenticationTokenSelectService(SystemUserCommonSelectUseCase userSelectUseCase, 
									 SystemUserMenuGroupSelectUseCase menuGroupSelectUseCase) {
		this.userSelectUseCase = userSelectUseCase;
		this.menuGroupSelectUseCase = menuGroupSelectUseCase;
	}
	
	@Override
	public AuthenticationToken select(String organizationCode, String userId, String sessionId, String ipAddress) {
		SystemUser user = userSelectUseCase.findUser(organizationCode, userId);
		
		List<MenuGroupSaveDTO> menuGroupList = menuGroupSelectUseCase.select(organizationCode, user.getStaffId().getStaffNo());
		
		return AuthenticationToken.of(user, menuGroupList, ipAddress, sessionId);
	}

}
