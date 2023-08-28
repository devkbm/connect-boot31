package com.like.system.biztypecode.application.port.out;

import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;

public interface BizCodeTypeSelectPort {
	BizCodeTypeSaveDTO select(String organizationCode, String typeId);
}
