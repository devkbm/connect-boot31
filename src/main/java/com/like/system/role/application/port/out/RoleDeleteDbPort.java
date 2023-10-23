package com.like.system.role.application.port.out;

public interface RoleDeleteDbPort {
	void delete(String organizationCode, String authorityCode);
}
