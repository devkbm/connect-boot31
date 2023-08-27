package com.like.system.authority.application.port.in;

import com.like.system.authority.domain.Authority;

public interface AuthoritySelectUseCase {
	Authority select(String organizationCode, String authorityCode);
}
