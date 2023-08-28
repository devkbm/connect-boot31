package com.like.system.biztypecode.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeType;
import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeTypeId;

public interface BizCodeTypeJpaRepository extends JpaRepository<JpaBizCodeType, JpaBizCodeTypeId> {

}
