package com.like.system.role.application.port.out;

import com.like.system.role.domain.Role;

public interface RoleSelectDbPort {
	Role find(String organizationCode, String authorityCode);
}
