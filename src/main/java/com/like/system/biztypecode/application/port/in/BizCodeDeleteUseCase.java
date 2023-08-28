package com.like.system.biztypecode.application.port.in;

public interface BizCodeDeleteUseCase {
	void delete(String organizationCode, String typeId, String code);
}
