package com.like.system.biztypecode.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.biztypecode.application.port.in.BizCodeTypeSelectAllUseCase;
import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;
import com.like.system.biztypecode.application.port.out.BizCodeTypeSelectAllPort;

@Service
public class BizCodeTypeSelectAllService implements BizCodeTypeSelectAllUseCase {

	BizCodeTypeSelectAllPort port;
	
	public BizCodeTypeSelectAllService(BizCodeTypeSelectAllPort port) {
		this.port = port;
	}

	@Override
	public List<BizCodeTypeSaveDTO> select(String organizationCode) {
		return this.port.select(organizationCode);
	}
	
}
