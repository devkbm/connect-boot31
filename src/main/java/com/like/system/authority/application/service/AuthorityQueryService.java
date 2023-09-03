package com.like.system.authority.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthority;
import com.like.system.authority.application.port.in.AuthorityQueryUseCase;
import com.like.system.authority.application.port.in.dto.AuthorityQueryRequestDTO;
import com.like.system.authority.application.port.out.AuthorityDbQueryPort;

@Transactional(readOnly = true)
@Service
public class AuthorityQueryService implements AuthorityQueryUseCase {

	private AuthorityDbQueryPort port;
	
	public AuthorityQueryService(AuthorityDbQueryPort port) {
		this.port = port;
	}
		
	@Override
	public List<JpaAuthority> getAuthorityList(AuthorityQueryRequestDTO condition) {
		return port.getAuthorityList(condition);
	}

}
