package com.like.system.biztypecode.application.port.out;

public interface BizCodeDeletePort {
	void delete(String organizationCode, String typeId, String code);
}
