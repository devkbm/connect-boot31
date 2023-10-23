package com.like.system.login.application.port.out;

import com.like.system.user.domain.SystemUser;

public interface SystemUserSelectDbPort {

	SystemUser select(String organizationCode, String userId);
}
