package com.like.system.file.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.file.application.port.in.FileServerSelectUseCase;
import com.like.system.file.application.port.out.FileInfoCommandDbPort;
import com.like.system.file.domain.FileInfo;

@Service
public class FileServerSelectService implements FileServerSelectUseCase {

	FileInfoCommandDbPort dbPort;

	FileServerSelectService(FileInfoCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public FileInfo select(String id) {
		return this.dbPort.getFileInfo(id);
	}

	@Override
	public List<FileInfo> select(List<String> ids) {
		return this.dbPort.getFileInfo(ids);
	}
}
