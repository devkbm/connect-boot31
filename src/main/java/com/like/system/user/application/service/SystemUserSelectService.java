package com.like.system.user.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.dto.SystemUserQueryDTO;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;
import com.like.system.user.application.port.out.SystemUserSelectDbPort;

@Service
public class SystemUserSelectService implements SystemUserSelectUseCase {

	SystemUserSelectDbPort dbPort;
	
	SystemUserSelectService(SystemUserSelectDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public SystemUserSaveDTO selectDTO(String organizationCode, String userId) {
		return SystemUserSaveDTO.toDTO(this.dbPort.select(organizationCode, userId));
	}

	@Override
	public List<SystemUserSaveDTO> selectList(SystemUserQueryDTO dto) {
		return this.dbPort.selectList(dto)
						  .stream()
						  .map(e -> SystemUserSaveDTO.toDTO(e))
						  .toList();
	}

}
