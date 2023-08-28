package com.like.system.biztypecode.adapter.out.persistence;

import java.util.List;

import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCode;

public interface BizCodeQueryRepository {
	
	List<JpaBizCode> getBizCodeList(String organizactionCode, String typeId);
}
