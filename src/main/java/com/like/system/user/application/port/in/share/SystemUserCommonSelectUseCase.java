package com.like.system.user.application.port.in.share;

import java.util.List;

import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;

public interface SystemUserCommonSelectUseCase {
	SystemUser findUser(String orginizationCode, String userId);
	
	List<SystemUser> findUsers(List<SystemUserId> userIds);
}
