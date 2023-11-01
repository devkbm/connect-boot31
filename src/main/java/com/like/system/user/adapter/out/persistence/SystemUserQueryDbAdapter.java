package com.like.system.user.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.application.port.dto.SystemUserQueryDTO;
import com.like.system.user.application.port.out.SystemUserQueryDbPort;
import com.like.system.user.domain.SystemUser;

@Repository
public class SystemUserQueryDbAdapter implements SystemUserQueryDbPort {

	SystemUserRepository repository;
	
	SystemUserQueryDbAdapter(SystemUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<SystemUser> selectList(SystemUserQueryDTO dto) {
		return this.repository.findAll(dto.getBooleanBuilder());
	}
	
	
}
