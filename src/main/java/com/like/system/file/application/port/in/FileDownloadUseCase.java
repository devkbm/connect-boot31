package com.like.system.file.application.port.in;

import java.io.File;

import jakarta.servlet.http.HttpServletResponse;

public interface FileDownloadUseCase {
	
	File getWebStaticFilePath(String fileName);
	
	void downloadFile(File file, HttpServletResponse response);
	
	void downloadFile(String fileInfoId, HttpServletResponse response);	
}
