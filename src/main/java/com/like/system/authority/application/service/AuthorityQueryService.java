package com.like.system.authority.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.authority.adapter.in.web.AuthorityDTO;
import com.like.system.authority.application.port.in.AuthorityQueryPort;
import com.like.system.authority.application.port.out.AuthorityQueryRepository;
import com.like.system.authority.domain.Authority;

@Transactional(readOnly = true)
@Service
public class AuthorityQueryService implements AuthorityQueryPort {

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
