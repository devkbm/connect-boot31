package com.like.system.biztypecode.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCode;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeId;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeMapper;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeType;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeTypeId;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeTypeMapper;
import com.like.system.biztypecode.adapter.out.persistence.jpa.repository.BizCodeRepository;
import com.like.system.biztypecode.adapter.out.persistence.jpa.repository.BizCodeTypeJpaRepository;
import com.like.system.biztypecode.application.port.in.dto.BizCodeSaveDTO;
import com.like.system.biztypecode.application.port.out.BizCodeDeletePort;
import com.like.system.biztypecode.application.port.out.BizCodeSavePort;
import com.like.system.biztypecode.application.port.out.BizCodeSelectPort;
import com.like.system.biztypecode.domain.BizCode;

@Repository
@Transactional
public class BizCodeAdapter implements BizCodeSelectPort, BizCodeSavePort, BizCodeDeletePort {
	
	BizCodeRepository repository;
	BizCodeTypeJpaRepository bizCodeTypeJpaRepository;
	
	public BizCodeAdapter(BizCodeRepository repository, BizCodeTypeJpaRepository bizCodeTypeJpaRepository) {
		this.repository = repository;
		this.bizCodeTypeJpaRepository = bizCodeTypeJpaRepository;
	}
	
	@Override
	public BizCode select(String organizationCode, String typeId, String code) {
		JpaBizCodeType jpaBizCodeType = bizCodeTypeJpaRepository.findById(new JpaBizCodeTypeId(organizationCode, typeId)).orElse(null);
		JpaBizCode jpaEntity = this.repository.findById(new JpaBizCodeId(organizationCode, typeId, code)).orElse(null);
		
		return JpaBizCodeMapper.toDomainEntity(jpaEntity, JpaBizCodeTypeMapper.toDomainEntity(jpaBizCodeType));
	}

	@Override
	public BizCodeSaveDTO selectDTO(String organizationCode, String typeId, String code) {
		JpaBizCode jpaEntity = this.repository.findById(new JpaBizCodeId(organizationCode, typeId, code)).orElse(null);
				
		return JpaBizCodeMapper.toDTO(jpaEntity);
	}
	
	@Override
	public void save(BizCode entity) {
		JpaBizCodeType jpaBizCodeType = bizCodeTypeJpaRepository.findById(new JpaBizCodeTypeId(entity.getId().getBizCodeTypeId().getOrganizationCode()
																							  ,entity.getId().getBizCodeTypeId().getTypeId())).orElse(null);
		
		this.repository.save(JpaBizCodeMapper.toJPAEntity(entity, jpaBizCodeType));		
	}
	
	@Override
	public void delete(String organizationCode, String typeId, String code) {
		this.repository.deleteById(new JpaBizCodeId(organizationCode, typeId, code));		
	}

}
