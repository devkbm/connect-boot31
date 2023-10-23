package com.like.system.user.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.application.port.dto.SystemUserQueryDTO;
import com.like.system.user.application.port.out.SystemUserSelectDbPort;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;

@Repository
@Transactional
public class SystemUserSelectAdapter implements SystemUserSelectDbPort {

	SystemUserRepository repository;
	
	SystemUserSelectAdapter(SystemUserRepository repository) {
		this.repository = repository;		
	}
	
	@Override
	public SystemUser select(String organizationCode, String userId) {
		return this.repository.findById(new SystemUserId(organizationCode, userId)).orElse(null);
	}	

	@Override
	public List<SystemUser> selectList(SystemUserQueryDTO dto) {
		return this.repository.findAll(dto.getBooleanBuilder());
	}

}
