package com.like.system.authority.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.authority.boundary.AuthorityDTO;
import com.like.system.authority.domain.Authority;
import com.like.system.authority.domain.AuthorityQueryRepository;

@Transactional(readOnly = true)
@Service
public class AuthorityQueryService {

	private AuthorityQueryRepository repository;
	
	public AuthorityQueryService(AuthorityQueryRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * 전체 권한 도메인 리스트를 조회한다.
	 * @return	권한 도메인 리스트
	 */
	public List<Authority> getAuthorityList(AuthorityDTO.SearchAuthority condition) {        									
        return repository.getAuthorityList(condition);
	}
}
