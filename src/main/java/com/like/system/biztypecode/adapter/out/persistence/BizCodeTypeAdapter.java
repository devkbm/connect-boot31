package com.like.system.biztypecode.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeType;
import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeTypeId;
import com.like.system.biztypecode.adapter.out.persistence.jpaentity.JpaBizCodeTypeMapper;
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
	public BizCodeTypeSaveDTO select(String organizationCode, String typeId) {
		JpaBizCodeType entity = this.repository.findById(new JpaBizCodeTypeId(organizationCode, typeId)).orElse(null);
		return JpaBizCodeTypeMapper.toDTO(entity);
	}
	
	@Override
	public void Save(BizCodeType entity) {
		this.repository.save(JpaBizCodeTypeMapper.toJPAEntity(entity));		
	}
	
	@Override
	public void delete(String organizationCode, String typeId) {
		this.repository.deleteById(new JpaBizCodeTypeId(organizationCode, typeId));		
	}

	@Override
	public List<BizCodeTypeSaveDTO> select(String organizationCode) {
		List<JpaBizCodeType> list = this.repository.findAll();
		return list.stream().map(e -> JpaBizCodeTypeMapper.toDTO(e)).toList();
	}
	
}
