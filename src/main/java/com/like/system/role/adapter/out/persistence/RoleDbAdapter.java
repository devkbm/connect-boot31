package com.like.system.role.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.role.adapter.out.persistence.jpa.entity.RoleMapper;
import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRole;
import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRoleId;
import com.like.system.role.adapter.out.persistence.jpa.repository.RoleJpaRepository;
import com.like.system.role.application.port.out.RoleCommandDbPort;
import com.like.system.role.domain.Role;

@Repository
@Transactional
public class RoleDbAdapter implements RoleCommandDbPort {

	RoleJpaRepository jpaRepository;
	
	public RoleDbAdapter(RoleJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public Role find(String organizationCode, String roleCode) {
		JpaRole entity = this.jpaRepository.findById(new JpaRoleId(organizationCode, roleCode)).orElse(null);
		
		return RoleMapper.toEntity(entity);
	}

	@Override
	public void save(Role role) {
		this.jpaRepository.save(RoleMapper.toJpaEntity(role));		
	}
	
	@Override
	public void delete(String organizationCode, String roleCode) {
		this.jpaRepository.deleteById(new JpaRoleId(organizationCode, roleCode));		
	}
}
