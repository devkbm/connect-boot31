package com.like.system.file.adapter.out.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.like.system.file.adapter.out.persistence.jpa.repository.FileInfoJpaRepository;
import com.like.system.file.application.port.out.FileInfoCommandDbPort;
import com.like.system.file.domain.FileInfo;

@Repository
public class FileInfoCommandDbAdapter implements FileInfoCommandDbPort  {

	FileInfoJpaRepository repository;
	
	FileInfoCommandDbAdapter(FileInfoJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public FileInfo getFileInfo(String id) {
		UUID uuid = UUID.fromString(id);
		
		return this.repository.findById(uuid).orElse(null);
	}

	@Override
	public FileInfo save(FileInfo entity) {
		return this.repository.save(entity);
	}

	@Override
	public List<FileInfo> save(List<FileInfo> entities) {
		return this.repository.saveAll(entities);
	}
	
	@Override
	public void delete(String id) {
		this.repository.deleteById(UUID.fromString(id));
	}



}
