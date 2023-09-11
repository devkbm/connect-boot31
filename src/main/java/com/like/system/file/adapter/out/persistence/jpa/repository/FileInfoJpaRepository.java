package com.like.system.file.adapter.out.persistence.jpa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;

import com.like.system.file.domain.FileInfo;

public interface FileInfoJpaRepository extends JpaRepository<FileInfo, UUID>, ListQuerydslPredicateExecutor<FileInfo> {
	
}
