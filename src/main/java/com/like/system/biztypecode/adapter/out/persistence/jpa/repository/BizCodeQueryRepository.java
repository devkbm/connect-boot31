package com.like.system.biztypecode.adapter.out.persistence.jpa.repository;

import java.util.List;

import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCode;

public interface BizCodeQueryRepository {
	
	List<JpaBizCode> getBizCodeList(String organizactionCode, String typeId);
}
