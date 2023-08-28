package com.like.system.biztypecode.application.port.out;

import com.like.system.biztypecode.application.port.in.dto.BizCodeSaveDTO;

public interface BizCodeSelectPort {
	BizCodeSaveDTO select(String organizationCode, String typeId, String code);
}
