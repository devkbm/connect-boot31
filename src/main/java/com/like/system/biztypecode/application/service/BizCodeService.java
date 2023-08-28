package com.like.system.biztypecode.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.adapter.out.persistence.BizCodeRepository;
import com.like.system.biztypecode.adapter.out.persistence.BizCodeTypeJpaRepository;
import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCode;
import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeId;
import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeType;
import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeTypeId;
import com.like.system.biztypecode.application.port.in.dto.BizCodeDTO;

@Service
@Transactional
public class BizCodeService {

	private BizCodeRepository repository;
	private BizCodeTypeJpaRepository bizTypeRepository;
	
	public BizCodeService(BizCodeRepository repository
						 ,BizCodeTypeJpaRepository bizTypeRepository) {
		this.repository = repository;
		this.bizTypeRepository = bizTypeRepository;
	}		
	
	public JpaBizCode getBizCode(String organizationCode, String typeId, String code) {				
		return repository.findById(new JpaBizCodeId(organizationCode, typeId, code)).orElse(null);
	}
	
	public void saveBizCode(BizCodeDTO.Form dto) {	
		JpaBizCodeType bizType = this.bizTypeRepository.findById(new JpaBizCodeTypeId(dto.organizationCode(), dto.typeId()))
													.orElseThrow(() -> new IllegalArgumentException("업무코드분류는 필수 값입니다."));
		JpaBizCode entity = this.getBizCode(dto.organizationCode(), dto.typeId(), dto.code());
					
		if (entity == null) {			
			entity = dto.newEntity(bizType);			
		} else {
			dto.modify(entity);
		}
		
		repository.save(entity);
	}
	
	public void deleteBizCode(String organizationCode, String typeId, String code) {
		repository.deleteById(new JpaBizCodeId(organizationCode, typeId, code));		
	}
	
}
