package com.like.system.file.application.port.out;

import java.util.List;

import com.like.system.file.domain.FileInfo;

public interface FileInfoCommandDbPort {
	FileInfo getFileInfo(String id);
	
	List<FileInfo> getFileInfo(List<String> ids);
	
	FileInfo save(FileInfo entity);
	
	List<FileInfo> save(List<FileInfo> entities);
	
	void delete(String id);
}
