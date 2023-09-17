package com.like.system.file.application.port.in;

import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.domain.FileInfo;

public interface FileUploadUseCase {

	FileInfo uploadFile(MultipartFile sourceFile, String userId, String pgmId);
}
