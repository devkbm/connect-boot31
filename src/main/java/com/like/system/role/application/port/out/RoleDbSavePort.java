package com.like.system.role.application.port.out;

import com.like.system.role.domain.Role;

public interface RoleDbSavePort {
	void save(Role authority);
}
