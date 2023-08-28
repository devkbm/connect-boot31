package com.like.system.biztypecode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.biztypecode.application.port.in.BizCodeTypeDeleteUseCase;
import com.like.system.biztypecode.application.port.out.BizCodeTypeDeletePort;

@Service
public class BizCodeTypeDeleteService implements BizCodeTypeDeleteUseCase {

	BizCodeTypeDeletePort port;
	
	public BizCodeTypeDeleteService(BizCodeTypeDeletePort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, String typeId) {
		this.port.delete(organizationCode, typeId);		
	}
	
	
}
