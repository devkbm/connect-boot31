package com.like.system.authority.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.authority.adapter.in.web.AuthorityDTO;
import com.like.system.authority.application.port.in.AuthorityPort;
import com.like.system.authority.application.port.out.AuthorityRepository;
import com.like.system.authority.domain.Authority;
import com.like.system.authority.domain.AuthorityId;

@Transactional
@Service
public class AuthorityService implements AuthorityPort {

	private AuthorityRepository repository;
	
	public AuthorityService(AuthorityRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * 권한 도메인을 조회한다.
	 * @param id	권한명
	 * @return	권한 도메인
	 */
	public Authority getAuthority(String organizationCode, String authorityCode) {
		return repository.findById(new AuthorityId(organizationCode, authorityCode)).orElse(null);
	}
	
	/**
	 * 권한 도메인을 등록한다.
	 * @param authority	권한 도메인
	 */
	public void createAuthority(AuthorityDTO.FormAuthority dto) {
		Authority authority = null;
		
		if (dto.id() != null) {
			authority = repository.findById(new AuthorityId(dto.organizationCode(), dto.authorityCode())).orElse(null);
		} 
		
		if (authority == null) {
			authority = dto.newEntity();
		} else {
			dto.modifyEntity(authority);
		}
		
		repository.save(authority);
	}		
		
	/**
	 * 권한 도메인을 삭제한다.
	 * @param id
	 */
	public void deleteAuthority(String organizationCode, String authorityCode) {
		repository.deleteById(new AuthorityId(organizationCode, authorityCode));
	}
}
