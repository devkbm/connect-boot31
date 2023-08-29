package com.like.system.file.adapter.out.persistence.jpa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.like.system.file.domain.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, UUID>, QuerydslPredicateExecutor<FileInfo> {
	
}
