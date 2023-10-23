package com.like.system.user.application.port.out;

import com.like.system.user.domain.SystemUser;

public interface SystemUserSaveDbPort {
	void save(SystemUser entity);
}
