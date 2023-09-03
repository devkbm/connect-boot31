package com.like.system.authority.application.port.out;

import java.util.List;

import com.like.system.authority.adapter.out.persistence.jpa.entity.JpaAuthority;
import com.like.system.authority.application.port.in.dto.AuthorityQueryRequestDTO;

public interface AuthorityDbQueryPort {

	/**
	 * 전체 권한 도메인 리스트를 조회한다.
	 * @return	권한 도메인 리스트
	 */
	List<JpaAuthority> getAuthorityList(AuthorityQueryRequestDTO condition);
	
}