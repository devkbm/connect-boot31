package com.like.system.biztypecode.application.port.out;

import java.util.List;

import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;

public interface BizCodeTypeSelectAllPort {
	List<BizCodeTypeSaveDTO> select(String organizationCode);
}
