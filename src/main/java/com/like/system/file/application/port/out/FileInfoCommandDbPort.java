package com.like.system.file.application.port.out;

import java.util.List;

import com.like.system.file.domain.FileInfo;

public interface FileInfoCommandDbPort {
	FileInfo getFileInfo(String id);
	
	FileInfo save(FileInfo entity);
	
	List<FileInfo> save(List<FileInfo> entities);
	
	void delete(String id);
}
