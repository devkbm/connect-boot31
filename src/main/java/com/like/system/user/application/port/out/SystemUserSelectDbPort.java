package com.like.system.user.application.port.out;

import java.util.List;

import com.like.system.user.application.port.dto.SystemUserQueryDTO;
import com.like.system.user.domain.SystemUser;

public interface SystemUserSelectDbPort {

	SystemUser select(String organizationCode, String userId);	
	
	List<SystemUser> selectList(SystemUserQueryDTO dto);
}
