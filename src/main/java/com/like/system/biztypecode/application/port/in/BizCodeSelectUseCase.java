package com.like.system.biztypecode.application.port.in;

import com.like.system.biztypecode.application.port.in.dto.BizCodeSaveDTO;

public interface BizCodeSelectUseCase {
	BizCodeSaveDTO select(String organizationCode, String typeId, String code);	
}
