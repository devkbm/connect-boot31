package com.like.system.file.application.port.in;

import jakarta.servlet.http.HttpServletResponse;

public interface FileDownloadUseCase {
	void downloadFile(String fileInfoId, HttpServletResponse response);
}
