package com.like.system.user.application.port.out;

import java.util.List;

import com.like.system.user.application.port.in.dto.SystemUserQueryConditionDTO;
import com.like.system.user.application.port.in.dto.SystemUserSaveDTO;
import com.like.system.user.domain.SystemUser;

public interface SystemUserSelectPort {

	SystemUser select(String organizationCode, String userId);
	
	SystemUserSaveDTO selectDTO(String organizationCode, String userId);
	
	List<SystemUserSaveDTO> selectList(SystemUserQueryConditionDTO dto);
}
