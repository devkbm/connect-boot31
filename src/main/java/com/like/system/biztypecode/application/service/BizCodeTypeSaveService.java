package com.like.system.biztypecode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.biztypecode.application.port.in.BizCodeTypeSaveUseCase;
import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeMapper;
import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;
import com.like.system.biztypecode.application.port.out.BizCodeTypeSavePort;

@Service
public class BizCodeTypeSaveService implements BizCodeTypeSaveUseCase {
	BizCodeTypeSavePort port;

	public BizCodeTypeSaveService(BizCodeTypeSavePort port) {
		this.port = port;
	}
	
	@Override
	public void save(BizCodeTypeSaveDTO dto) {
		this.port.Save(BizCodeTypeMapper.toEntity(dto));		
	}
}
