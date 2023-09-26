package com.like.hrm.staff.application.port.in;

import jakarta.servlet.http.HttpServletResponse;

public interface StaffImageDownloadUseCase {
	HttpServletResponse downloadImageFile(String organizationCode, String staffNo, HttpServletResponse response) throws Exception;
}
