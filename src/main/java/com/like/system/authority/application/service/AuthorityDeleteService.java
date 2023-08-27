package com.like.system.authority.application.service;

import org.springframework.stereotype.Service;

import com.like.system.authority.application.port.in.AuthorityDeleteUseCase;
import com.like.system.authority.application.port.out.AuthorityDeletePort;

@Service
public class AuthorityDeleteService implements AuthorityDeleteUseCase {

	AuthorityDeletePort port;

	public AuthorityDeleteService(AuthorityDeletePort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, String authorityCode) {
		this.port.delete(organizationCode, authorityCode);		
	}
	
}
