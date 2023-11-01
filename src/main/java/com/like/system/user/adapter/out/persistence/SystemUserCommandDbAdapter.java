package com.like.system.user.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.application.port.out.SystemUserCommandDbPort;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;


@Repository
public class SystemUserCommandDbAdapter implements SystemUserCommandDbPort {

	SystemUserRepository repository;	
	
	SystemUserCommandDbAdapter(SystemUserRepository repository) {
		this.repository = repository;			
	}
	
	@Override
	public SystemUser select(String organizationCode, String userId) { 
		return this.repository.findById(new SystemUserId(organizationCode, userId)).orElse(null);
	}
	
	@Override
	public void save(SystemUser entity) {
		this.repository.save(entity);		
	}
	
	@Override
	public void delete(String organizationCode, String userId) {
		this.repository.deleteById(new SystemUserId(organizationCode, userId));		
	}
				
}
