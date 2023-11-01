package com.like.system.user.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.user.application.port.dto.SystemUserQueryDTO;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.in.SystemUserQueryUseCase;
import com.like.system.user.application.port.out.SystemUserQueryDbPort;

@Transactional(readOnly = true)
@Service
public class SystemUserQueryService implements SystemUserQueryUseCase {

	SystemUserQueryDbPort dbPort;
	
	SystemUserQueryService(SystemUserQueryDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<SystemUserSaveDTO> selectList(SystemUserQueryDTO dto) {
		return this.dbPort.selectList(dto)
						  .stream()
						  .map(e -> SystemUserSaveDTO.toDTO(e))
						  .toList();
	}

}
