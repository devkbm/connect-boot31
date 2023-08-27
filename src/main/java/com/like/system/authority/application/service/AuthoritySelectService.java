package com.like.system.authority.application.service;

import org.springframework.stereotype.Service;

import com.like.system.authority.application.port.in.AuthoritySelectUseCase;
import com.like.system.authority.application.port.out.AuthoritySelectPort;
import com.like.system.authority.domain.Authority;

@Service
public class AuthoritySelectService implements AuthoritySelectUseCase {

	private AuthoritySelectPort port;
	
	public AuthoritySelectService(AuthoritySelectPort port) {
		this.port = port;
	}
	
	@Override
	public Authority select(String organizationCode, String authorityCode) { 
		return port.find(organizationCode, authorityCode);
	}

}
