package com.like.system.authority.application.port.in;

import com.like.system.authority.adapter.in.web.AuthorityDTO;
import com.like.system.authority.domain.Authority;

public interface AuthorityPort {

	Authority getAuthority(String organizationCode, String authorityCode);
	
	void createAuthority(AuthorityDTO.FormAuthority dto);
	
	void deleteAuthority(String organizationCode, String authorityCode);
}
