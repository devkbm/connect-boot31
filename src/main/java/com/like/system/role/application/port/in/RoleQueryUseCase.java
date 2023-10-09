package com.like.system.role.application.port.in;

import java.util.List;

import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRole;
import com.like.system.role.application.port.dto.RoleQueryDTO;

public interface RoleQueryUseCase {
	List<JpaRole> getAuthorityList(RoleQueryDTO condition);
}
