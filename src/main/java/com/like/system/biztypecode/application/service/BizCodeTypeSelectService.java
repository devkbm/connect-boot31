package com.like.system.biztypecode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.biztypecode.application.port.in.BizCodeTypeSelectUseCase;
import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;
import com.like.system.biztypecode.application.port.out.BizCodeTypeSelectPort;

@Service
public class BizCodeTypeSelectService implements BizCodeTypeSelectUseCase {

	BizCodeTypeSelectPort port;
	
	public BizCodeTypeSelectService(BizCodeTypeSelectPort port) {
		this.port = port;
	}

	@Override
	public BizCodeTypeSaveDTO select(String organizationCode, String typeId) {
		return this.port.selectDTO(organizationCode, typeId);
	}
	
}
