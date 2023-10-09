package com.like.system.role.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.role.adapter.out.persistence.jpa.entity.RoleMapper;
import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRole;
import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRoleId;
import com.like.system.role.adapter.out.persistence.jpa.repository.RoleJpaRepository;
import com.like.system.role.application.port.out.RoleDbDeletePort;
import com.like.system.role.application.port.out.RoleDbSavePort;
import com.like.system.role.application.port.out.RoleDbSelectPort;
import com.like.system.role.domain.Role;

@Repository
@Transactional
public class RoleDbAdapter implements RoleDbSelectPort, RoleDbSavePort, RoleDbDeletePort {

	RoleJpaRepository jpaRepository;
	
	public RoleDbAdapter(RoleJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public Role find(String organizationCode, String authorityCode) {
		JpaRole entity = this.jpaRepository.findById(new JpaRoleId(organizationCode, authorityCode)).orElse(null);
		
		return RoleMapper.toEntity(entity);
	}

	@Override
	public void save(Role authority) {
		this.jpaRepository.save(RoleMapper.toJpaEntity(authority));		
	}
	
	@Override
	public void delete(String organizationCode, String authorityCode) {
		this.jpaRepository.deleteById(new JpaRoleId(organizationCode, authorityCode));		
	}
}
