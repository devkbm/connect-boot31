package com.like.system.biztypecode.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeType;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeTypeId;

public interface BizCodeTypeJpaRepository extends JpaRepository<JpaBizCodeType, JpaBizCodeTypeId> {

}
