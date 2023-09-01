package com.like.system.user.application.port.in;

import jakarta.servlet.http.HttpServletResponse;

public interface SystemUserImageFileUseCase {
	HttpServletResponse downloadImageFile(String organizationCode, String userId, HttpServletResponse response) throws Exception;
}
