package com.like.system.authority.application.port.in;

import java.util.List;

import com.like.system.authority.adapter.in.web.AuthorityDTO;
import com.like.system.authority.domain.Authority;

public interface AuthorityQueryPort {

	List<Authority> getAuthorityList(AuthorityDTO.SearchAuthority condition);
}
