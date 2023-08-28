package com.like.system.biztypecode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.biztypecode.application.port.in.BizCodeSaveUseCase;
import com.like.system.biztypecode.application.port.in.dto.BizCodeMapper;
import com.like.system.biztypecode.application.port.in.dto.BizCodeSaveDTO;
import com.like.system.biztypecode.application.port.out.BizCodeSavePort;
import com.like.system.biztypecode.application.port.out.BizCodeTypeSelectPort;
import com.like.system.biztypecode.domain.BizCodeType;

@Service
public class BizCodeSaveService implements BizCodeSaveUseCase {

	BizCodeSavePort port;
	BizCodeTypeSelectPort bizCodeTypeSelectPort;
	
	public BizCodeSaveService(BizCodeSavePort port, BizCodeTypeSelectPort bizCodeTypeSelectPort) {	
		this.port = port;
		this.bizCodeTypeSelectPort = bizCodeTypeSelectPort;
	}
	
	@Override
	public void save(BizCodeSaveDTO dto) {
		BizCodeType bizCodeType = bizCodeTypeSelectPort.select(dto.organizationCode(), dto.typeId());
		this.port.save(BizCodeMapper.toEntity(dto, bizCodeType));
	}
	
}
