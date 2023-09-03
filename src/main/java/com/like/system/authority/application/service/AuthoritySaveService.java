package com.like.system.authority.application.service;

import org.springframework.stereotype.Service;

import com.like.system.authority.application.port.in.AuthoritySaveUseCase;
import com.like.system.authority.application.port.in.dto.AuthoritySaveDTO;
import com.like.system.authority.application.port.out.AuthorityDbSavePort;
import com.like.system.authority.application.port.out.AuthorityDbSelectPort;
import com.like.system.authority.domain.Authority;

@Service
public class AuthoritySaveService implements AuthoritySaveUseCase {

	AuthorityDbSelectPort selectPort;
	AuthorityDbSavePort savePort;
	
	public AuthoritySaveService(AuthorityDbSelectPort selectPort, AuthorityDbSavePort savePort) {
		this.selectPort = selectPort;
		this.savePort = savePort;
	}

	@Override
	public void save(AuthoritySaveDTO dto) {
		Authority authority = selectPort.find(dto.organizationCode(), dto.authorityCode());			
		
		if (authority == null) {
			authority = dto.newEntity();
		} else {
			dto.modifyEntity(authority);
		}
		
		savePort.save(authority);		
	}
	
}
