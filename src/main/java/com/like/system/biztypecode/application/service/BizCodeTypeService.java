package com.like.system.biztypecode.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.adapter.out.persistence.BizCodeTypeJpaRepository;
import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeType;
import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeTypeId;
import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeDTO;

@Service
@Transactional
public class BizCodeTypeService {

	private BizCodeTypeJpaRepository repository;
	
	public BizCodeTypeService(BizCodeTypeJpaRepository repository) {
		this.repository = repository;
	}
	
	public List<JpaBizCodeType> getBizCodeTypeAllList() {
		return repository.findAll();
	}
	
	public JpaBizCodeType getBizCodeType(String organizationCode, String typeId) {
		return repository.findById(new JpaBizCodeTypeId(organizationCode, typeId)).orElse(null);
	}
	
	public void saveBizCodeType(BizCodeTypeDTO.Form dto) {
		JpaBizCodeType entity = this.getBizCodeType(dto.organizationCode(), dto.typeId());
		
		if (entity == null) {
			entity = dto.newEntity();			
		} else {
			dto.modify(entity);
		}
		
		repository.save(entity);
	}
	
	public void deleteBizCodeType(String organizationCode, String typeId) {
		repository.deleteById(new JpaBizCodeTypeId(organizationCode, typeId));
	}		
		
}
