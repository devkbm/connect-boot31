package com.like.system.file.application.port.in;

import java.io.File;

import jakarta.servlet.http.HttpServletResponse;

public interface FileDownloadUseCase {
	/*
	File getWebStaticFilePath(String fileName);
	
	void downloadFile(File file, HttpServletResponse response);
	*/
	void download(String fileInfoId, HttpServletResponse response);
	
	void downloadWebStaticPath(String uploadFileName, String downloadFileName, HttpServletResponse response);
}
