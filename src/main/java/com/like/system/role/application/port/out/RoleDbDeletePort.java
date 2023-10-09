package com.like.system.role.application.port.out;

public interface RoleDbDeletePort {
	void delete(String organizationCode, String authorityCode);
}
