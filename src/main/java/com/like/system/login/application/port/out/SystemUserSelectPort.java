package com.like.system.login.application.port.out;

import com.like.system.user.domain.SystemUser;

public interface SystemUserSelectPort {

	SystemUser select(String organizationCode, String userId);
}
