package com.like.system.user.application.port.in;

import com.like.system.user.application.port.dto.PasswordChangeRequestDTO;

public interface SystemUserPasswordChangeUseCase {
	void changePassword(PasswordChangeRequestDTO dto);
}
