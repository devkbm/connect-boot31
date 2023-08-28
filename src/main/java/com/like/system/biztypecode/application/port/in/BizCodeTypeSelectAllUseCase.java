package com.like.system.biztypecode.application.port.in;

import java.util.List;

import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;

public interface BizCodeTypeSelectAllUseCase {

	List<BizCodeTypeSaveDTO> select(String organizationCode);
}
