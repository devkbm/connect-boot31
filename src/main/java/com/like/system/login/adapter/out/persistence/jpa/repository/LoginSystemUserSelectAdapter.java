package com.like.system.login.adapter.out.persistence.jpa.repository;

import org.springframework.stereotype.Repository;

import com.like.system.login.application.port.out.SystemUserSelectPort;
import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;

@Repository
public class LoginSystemUserSelectAdapter implements SystemUserSelectPort {

	SystemUserRepository repository;
	
	public LoginSystemUserSelectAdapter(SystemUserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public SystemUser select(String organizationCode, String userId) {		
		return this.repository.findById(new SystemUserId(organizationCode, userId)).orElse(null);
	}

}
