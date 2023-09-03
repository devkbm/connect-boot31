package com.like.system.authority.application.port.out;

public interface AuthorityDbDeletePort {
	void delete(String organizationCode, String authorityCode);
}
