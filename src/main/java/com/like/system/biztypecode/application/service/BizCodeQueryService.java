package com.like.system.biztypecode.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCode;
import com.like.system.biztypecode.adapter.out.persistence.jpa.repository.BizCodeQueryRepository;

@Service
@Transactional(readOnly = true)
public class BizCodeQueryService {

	private BizCodeQueryRepository repository;
	
	public BizCodeQueryService(BizCodeQueryRepository repository) {
		this.repository = repository;
	}
	
	public List<JpaBizCode> getBizCodeAllList(String organizationCode, String typeId) {
		return this.repository.getBizCodeList(organizationCode, typeId);
	}
}
