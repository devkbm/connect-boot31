package com.like.system.authority.domain;

import java.util.List;

import com.like.system.authority.boundary.AuthorityDTO;

public interface AuthorityQueryRepository {

	/**
	 * 전체 권한 도메인 리스트를 조회한다.
	 * @return	권한 도메인 리스트
	 */
	List<Authority> getAuthorityList(AuthorityDTO.SearchAuthority condition);
	
}
