package com.like.system.user.application.service;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.dto.PasswordChangeRequestDTO;
import com.like.system.user.application.port.in.SystemUserPasswordChangeUseCase;
import com.like.system.user.application.port.in.SystemUserPasswordInitUseCase;
import com.like.system.user.application.port.out.SystemUserSaveDbPort;
import com.like.system.user.application.port.out.SystemUserSelectDbPort;
import com.like.system.user.domain.SystemUser;

@Service
public class SystemUserPasswordService implements SystemUserPasswordChangeUseCase, SystemUserPasswordInitUseCase {

	SystemUserSelectDbPort selectPort;
	SystemUserSaveDbPort savePort;
	
	SystemUserPasswordService(SystemUserSelectDbPort selectPort
								   ,SystemUserSaveDbPort savePort) {
		this.selectPort = selectPort;
		this.savePort = savePort;
	}
	
	@Override
	public void changePassword(PasswordChangeRequestDTO dto) {
		SystemUser user = selectPort.select(dto.organizationCode(), dto.userId());			
		
		if ( user.isVaild(dto.beforePassword()) ) {
			user.changePassword(dto.afterPassword());
		} 		
	}

	@Override
	public void initPassword(String organizationCode, String userId) {
		SystemUser user = selectPort.select(organizationCode, userId);
		
		user.initPassword();		
	}

}
