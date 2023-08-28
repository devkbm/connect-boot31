package com.like.system.biztypecode.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeType;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeTypeId;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeTypeMapper;
import com.like.system.biztypecode.adapter.out.persistence.jpa.repository.BizCodeTypeJpaRepository;
import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeSaveDTO;
import com.like.system.biztypecode.application.port.out.BizCodeTypeDeletePort;
import com.like.system.biztypecode.application.port.out.BizCodeTypeSavePort;
import com.like.system.biztypecode.application.port.out.BizCodeTypeSelectAllPort;
import com.like.system.biztypecode.application.port.out.BizCodeTypeSelectPort;
import com.like.system.biztypecode.domain.BizCodeType;

@Repository
@Transactional
public class BizCodeTypeAdapter implements BizCodeTypeSelectPort, BizCodeTypeSavePort, BizCodeTypeDeletePort, BizCodeTypeSelectAllPort {

	BizCodeTypeJpaRepository repository;
	
	public BizCodeTypeAdapter(BizCodeTypeJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public BizCodeType select(String organizationCode, String typeId) {
		JpaBizCodeType jpaEntity = this.repository.findById(new JpaBizCodeTypeId(organizationCode, typeId)).orElse(null);
		
		return JpaBizCodeTypeMapper.toDomainEntity(jpaEntity);
	}
	
	@Override
	public List<BizCodeTypeSaveDTO> select(String organizationCode) {
		List<JpaBizCodeType> list = this.repository.findAll();
		return list.stream().map(e -> JpaBizCodeTypeMapper.toDTO(e)).toList();
	}
	
	@Override
	public BizCodeTypeSaveDTO selectDTO(String organizationCode, String typeId) {
		JpaBizCodeType jpaEntity = this.repository.findById(new JpaBizCodeTypeId(organizationCode, typeId)).orElse(null);
		return JpaBizCodeTypeMapper.toDTO(jpaEntity);
	}
	
	@Override
	public void Save(BizCodeType entity) {
		this.repository.save(JpaBizCodeTypeMapper.toJPAEntity(entity));		
	}
	
	@Override
	public void delete(String organizationCode, String typeId) {
		this.repository.deleteById(new JpaBizCodeTypeId(organizationCode, typeId));		
	}
	
}
