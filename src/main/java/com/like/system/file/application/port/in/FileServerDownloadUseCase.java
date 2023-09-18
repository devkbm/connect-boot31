package com.like.system.file.application.port.in;

import jakarta.servlet.http.HttpServletResponse;

public interface FileServerDownloadUseCase {
	
	void download(String fileInfoId, HttpServletResponse response);	
}
