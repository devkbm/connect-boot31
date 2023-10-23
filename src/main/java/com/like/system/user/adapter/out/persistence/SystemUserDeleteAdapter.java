package com.like.system.user.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.application.port.out.SystemUserDeleteDbPort;
import com.like.system.user.domain.SystemUserId;

@Repository
@Transactional
public class SystemUserDeleteAdapter implements SystemUserDeleteDbPort {

	SystemUserRepository repository;
	
	SystemUserDeleteAdapter(SystemUserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void delete(String organizationCode, String userId) {
		this.repository.deleteById(new SystemUserId(organizationCode, userId));		
	}

}
