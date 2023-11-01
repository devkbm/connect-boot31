package com.like.system.role.application.port.out;

import com.like.system.role.domain.Role;

public interface RoleCommandDbPort {
	Role find(String organizationCode, String roleCode);
	
	void save(Role entity);
	
	void delete(String organizationCode, String roleCode);
}
