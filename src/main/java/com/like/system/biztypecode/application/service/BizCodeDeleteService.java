package com.like.system.biztypecode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.biztypecode.application.port.in.BizCodeDeleteUseCase;
import com.like.system.biztypecode.application.port.out.BizCodeDeletePort;

@Service
public class BizCodeDeleteService implements BizCodeDeleteUseCase {

	BizCodeDeletePort port;
	
	public BizCodeDeleteService(BizCodeDeletePort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, String typeId, String code) {
		this.port.delete(organizationCode, typeId, code);		
	}

	
}
