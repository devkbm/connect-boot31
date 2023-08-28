package com.like.system.biztypecode.application.port.in;

import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;

public interface BizCodeTypeSelectUseCase {
	
	BizCodeTypeSaveDTO select(String organizationCode, String typeId);
}
