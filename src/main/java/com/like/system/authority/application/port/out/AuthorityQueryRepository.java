package com.like.system.authority.application.port.out;

import java.util.List;

import com.like.system.authority.adapter.in.web.AuthorityDTO;
import com.like.system.authority.domain.Authority;

public interface AuthorityQueryRepository {

	/**
	 * 전체 권한 도메인 리스트를 조회한다.
	 * @return	권한 도메인 리스트
	 */
	List<Authority> getAuthorityList(AuthorityDTO.SearchAuthority condition);
	
}
