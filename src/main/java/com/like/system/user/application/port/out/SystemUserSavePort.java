package com.like.system.user.application.port.out;

import com.like.system.user.application.port.in.dto.SystemUserSaveDTO;
import com.like.system.user.domain.SystemUser;

public interface SystemUserSavePort {
	void save(SystemUser entity);
	
	void save(SystemUserSaveDTO dto);
}
