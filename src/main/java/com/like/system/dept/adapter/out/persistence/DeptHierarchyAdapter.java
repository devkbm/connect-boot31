package com.like.system.dept.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.dept.adapter.out.persistence.jpa.repository.DeptJpaQueryRepository;
import com.like.system.dept.application.port.in.dto.DeptQueryConditionDTO;
import com.like.system.dept.application.port.in.dto.DeptHierarchyResponse;
import com.like.system.dept.application.port.out.DeptHierarchySelectPort;

@Repository
@Transactional(readOnly = true)
public class DeptHierarchyAdapter implements DeptHierarchySelectPort {

	DeptJpaQueryRepository repository;
	
	public DeptHierarchyAdapter(DeptJpaQueryRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<DeptHierarchyResponse> select(DeptQueryConditionDTO dto) {
		return this.repository.getDeptHierarchy(dto.organizationCode());
	}
}