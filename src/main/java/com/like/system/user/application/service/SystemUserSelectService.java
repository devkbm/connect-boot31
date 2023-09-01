package com.like.system.user.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.in.SystemUserSelectUseCase;
import com.like.system.user.application.port.in.dto.SystemUserQueryConditionDTO;
import com.like.system.user.application.port.in.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.out.SystemUserSelectPort;

@Service
public class SystemUserSelectService implements SystemUserSelectUseCase {

	SystemUserSelectPort port;
	
	SystemUserSelectService(SystemUserSelectPort port) {
		this.port = port;
	}
	
	@Override
	public SystemUserSaveDTO selectDTO(String organizationCode, String userId) {
		return this.port.selectDTO(organizationCode, userId);
	}

	@Override
	public List<SystemUserSaveDTO> selectList(SystemUserQueryConditionDTO dto) {
		return this.port.selectList(dto);
	}

}
