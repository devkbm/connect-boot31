package com.like.system.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.user.application.port.dto.PasswordChangeRequestDTO;
import com.like.system.user.application.port.in.SystemUserPasswordChangeUseCase;
import com.like.system.user.application.port.in.SystemUserPasswordInitUseCase;
import com.like.system.user.application.port.out.SystemUserCommandDbPort;
import com.like.system.user.domain.SystemUser;

@Transactional
@Service
public class SystemUserPasswordService implements SystemUserPasswordChangeUseCase, SystemUserPasswordInitUseCase {

	SystemUserCommandDbPort dbPort;
	
	SystemUserPasswordService(SystemUserCommandDbPort dbPort) {
		this.dbPort = dbPort;		
	}
	
	@Override
	public void changePassword(PasswordChangeRequestDTO dto) {
		SystemUser user = dbPort.select(dto.organizationCode(), dto.userId());			
		
		if ( user.isVaild(dto.beforePassword()) ) {
			user.changePassword(dto.afterPassword());
		} 		
	}

	@Override
	public void initPassword(String organizationCode, String userId) {
		SystemUser user = dbPort.select(organizationCode, userId);
		
		user.initPassword();		
	}

}
