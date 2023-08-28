package com.like.system.biztypecode.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCode;
import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeId;

public interface BizCodeRepository extends JpaRepository<JpaBizCode, JpaBizCodeId> {

}
