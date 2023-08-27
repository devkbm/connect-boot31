package com.like.system.authority.application.port.in;

import java.util.List;

import com.like.system.authority.adapter.out.persistence.JpaAuthority;
import com.like.system.authority.application.port.in.dto.AuthorityQueryRequestDTO;

public interface AuthorityQueryUseCase {
	List<JpaAuthority> getAuthorityList(AuthorityQueryRequestDTO condition);
}
