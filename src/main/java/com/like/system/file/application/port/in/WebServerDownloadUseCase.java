package com.like.system.file.application.port.in;

import jakarta.servlet.http.HttpServletResponse;

public interface WebServerDownloadUseCase {
	void downloadWebStaticPath(String uploadFileName, String downloadFileName, HttpServletResponse response);
}
