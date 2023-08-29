package com.like.system.file.application.port.in;

import java.io.OutputStream;

import com.like.system.file.domain.FileInfo;

public interface FileDownloadUseCase {
	void downloadFile(FileInfo fileInfo, OutputStream os);
}
