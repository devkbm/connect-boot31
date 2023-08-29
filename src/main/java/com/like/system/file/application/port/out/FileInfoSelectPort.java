package com.like.system.file.application.port.out;

import com.like.system.file.domain.FileInfo;

public interface FileInfoSelectPort {
	FileInfo getFileInfo(String id);
}
