package com.like.system.role.application.port.in;

import com.like.system.role.domain.Role;

public interface RoleSelectUseCase {
	Role select(String organizationCode, String roleCode);
}
