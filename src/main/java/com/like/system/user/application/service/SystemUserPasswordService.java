package com.like.system.user.application.service;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.in.SystemUserPasswordChangeUseCase;
import com.like.system.user.application.port.in.SystemUserPasswordInitUseCase;
import com.like.system.user.application.port.in.dto.PasswordChangeRequestDTO;
import com.like.system.user.application.port.out.SystemUserDbSavePort;
import com.like.system.user.application.port.out.SystemUserDbSelectPort;
import com.like.system.user.domain.SystemUser;

@Service
public class SystemUserPasswordService implements SystemUserPasswordChangeUseCase, SystemUserPasswordInitUseCase {

	SystemUserDbSelectPort selectPort;
	SystemUserDbSavePort savePort;
	
	SystemUserPasswordService(SystemUserDbSelectPort selectPort
								   ,SystemUserDbSavePort savePort) {
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