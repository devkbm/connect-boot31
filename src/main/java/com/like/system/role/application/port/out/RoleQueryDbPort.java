package com.like.system.role.application.port.out;

import java.util.List;

import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRole;
import com.like.system.role.application.port.dto.RoleQueryDTO;

public interface RoleQueryDbPort {

	/**
	 * 전체 권한 도메인 리스트를 조회한다.
	 * @return	권한 도메인 리스트
	 */
	List<JpaRole> getRoleList(RoleQueryDTO dto);
	
}
