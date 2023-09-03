package com.like.system.authority.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.authority.adapter.out.persistence.jpa.entity.AuthorityMapper;
import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthority;
import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthorityId;
import com.like.system.authority.adapter.out.persistence.jpa.repository.AuthorityJpaRepository;
import com.like.system.authority.application.port.out.AuthorityDbDeletePort;
import com.like.system.authority.application.port.out.AuthorityDbSavePort;
import com.like.system.authority.application.port.out.AuthorityDbSelectPort;
import com.like.system.authority.domain.Authority;

@Repository
@Transactional
public class AuthorityDbAdapter implements AuthorityDbSelectPort, AuthorityDbSavePort, AuthorityDbDeletePort {

	AuthorityJpaRepository jpaRepository;
	
	public AuthorityDbAdapter(AuthorityJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public Authority find(String organizationCode, String authorityCode) {
		JpaAuthority entity = this.jpaRepository.findById(new JpaAuthorityId(organizationCode, authorityCode)).orElse(null);
		
		return AuthorityMapper.toEntity(entity);
	}

	@Override
	public void save(Authority authority) {
		this.jpaRepository.save(AuthorityMapper.toJpaEntity(authority));		
	}
	
	@Override
	public void delete(String organizationCode, String authorityCode) {
		this.jpaRepository.deleteById(new JpaAuthorityId(organizationCode, authorityCode));		
	}
}
