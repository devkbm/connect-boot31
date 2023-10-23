package com.like.system.user.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.application.port.out.SystemUserSaveDbPort;
import com.like.system.user.domain.SystemUser;


@Repository
public class SystemUserSaveAdapter implements SystemUserSaveDbPort {

	SystemUserRepository repository;	
	
	SystemUserSaveAdapter(SystemUserRepository repository) {
		this.repository = repository;			
	}
	
	@Override
	public void save(SystemUser entity) {
		this.repository.save(entity);		
	}
				
}
