package com.like.system.biztypecode.application.port.out;

import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;
import com.like.system.biztypecode.domain.BizCodeType;

public interface BizCodeTypeSelectPort {
	BizCodeType select(String organizationCode, String typeId);
	
	BizCodeTypeSaveDTO selectDTO(String organizationCode, String typeId);	
}
