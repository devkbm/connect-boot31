package com.like.system.file.application.port.out;

import com.like.system.file.domain.FileInfo;

public interface FileInfoDbSelectPort {
	FileInfo getFileInfo(String id);
}
