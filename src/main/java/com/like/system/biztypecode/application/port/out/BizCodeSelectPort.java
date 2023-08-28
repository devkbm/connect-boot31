package com.like.system.biztypecode.application.port.out;

import com.like.system.biztypecode.application.port.in.dto.BizCodeSaveDTO;
import com.like.system.biztypecode.domain.BizCode;

public interface BizCodeSelectPort {
	BizCode select(String organizationCode, String typeId, String code);
	
	BizCodeSaveDTO selectDTO(String organizationCode, String typeId, String code);
}
