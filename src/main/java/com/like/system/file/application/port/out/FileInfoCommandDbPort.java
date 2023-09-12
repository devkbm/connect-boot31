package com.like.system.file.application.port.out;

import com.like.system.file.domain.FileInfo;

public interface FileInfoCommandDbPort {
	FileInfo getFileInfo(String id);
	
	FileInfo save(FileInfo entity);
	
	void delete(String id);
}
