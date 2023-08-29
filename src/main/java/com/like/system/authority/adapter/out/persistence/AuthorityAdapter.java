package com.like.system.authority.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.authority.adapter.out.persistence.jpa.entity.AuthorityMapper;
import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthority;
import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthorityId;
import com.like.system.authority.adapter.out.persistence.jpa.repository.AuthorityJpaRepository;
import com.like.system.authority.application.port.out.AuthorityDeletePort;
import com.like.system.authority.application.port.out.AuthoritySavePort;
import com.like.system.authority.application.port.out.AuthoritySelectPort;
import com.like.system.authority.domain.Authority;

@Repository
@Transactional
public class AuthorityAdapter implements AuthoritySelectPort, AuthoritySavePort, AuthorityDeletePort {

	AuthorityJpaRepository jpaRepository;
	
	public AuthorityAdapter(AuthorityJpaRepository jpaRepository) {
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
