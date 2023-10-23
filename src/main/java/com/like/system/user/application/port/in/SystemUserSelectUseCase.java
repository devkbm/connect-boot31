package com.like.system.user.application.port.in;

import java.util.List;

import com.like.system.user.application.port.dto.SystemUserQueryDTO;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;

public interface SystemUserSelectUseCase {
	SystemUserSaveDTO selectDTO(String organizationCode, String userId);
	
	List<SystemUserSaveDTO> selectList(SystemUserQueryDTO dto);
}
