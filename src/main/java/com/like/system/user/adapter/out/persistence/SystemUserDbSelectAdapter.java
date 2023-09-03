package com.like.system.user.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.application.port.in.dto.SystemUserQueryConditionDTO;
import com.like.system.user.application.port.in.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.out.SystemUserDbSelectPort;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;

@Repository
@Transactional
public class SystemUserDbSelectAdapter implements SystemUserDbSelectPort {

	SystemUserRepository repository;
	
	SystemUserDbSelectAdapter(SystemUserRepository repository) {
		this.repository = repository;		
	}
	
	@Override
	public SystemUser select(String organizationCode, String userId) {
		return this.repository.findById(new SystemUserId(organizationCode, userId)).orElse(null);
	}
	
	@Override
	public SystemUserSaveDTO selectDTO(String organizationCode, String userId) {
		SystemUser entity = this.repository.findById(new SystemUserId(organizationCode, userId)).orElse(null); 
		return SystemUserSaveDTO.toDTO(entity);
	}

	@Override
	public List<SystemUserSaveDTO> selectList(SystemUserQueryConditionDTO dto) {
		return this.repository.findAll(dto.getBooleanBuilder())
							  .stream()
							  .map(e -> SystemUserSaveDTO.toDTO(e))
							  .toList();
	}

}
