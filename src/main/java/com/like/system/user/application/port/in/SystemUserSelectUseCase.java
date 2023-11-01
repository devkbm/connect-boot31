package com.like.system.user.application.port.in;

import com.like.system.user.application.port.dto.SystemUserSaveDTO;

public interface SystemUserSelectUseCase {
	SystemUserSaveDTO selectDTO(String organizationCode, String userId);	
}
