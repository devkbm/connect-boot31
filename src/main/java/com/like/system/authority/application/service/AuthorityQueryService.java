package com.like.system.authority.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.authority.adapter.out.persistence.JpaAuthority;
import com.like.system.authority.application.port.in.AuthorityQueryUseCase;
import com.like.system.authority.application.port.in.dto.AuthorityQueryRequestDTO;
import com.like.system.authority.application.port.out.AuthorityQueryPort;

@Transactional(readOnly = true)
@Service
public class AuthorityQueryService implements AuthorityQueryUseCase {

	private AuthorityQueryPort port;
	
	public AuthorityQueryService(AuthorityQueryPort port) {
		this.port = port;
	}
		
	@Override
	public List<JpaAuthority> getAuthorityList(AuthorityQueryRequestDTO condition) {
		return port.getAuthorityList(condition);
	}

}
