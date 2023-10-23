package com.like.system.dept.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.dept.adapter.out.persistence.jpa.repository.DeptJpaQueryRepository;
import com.like.system.dept.application.port.dto.DeptHierarchyResponse;
import com.like.system.dept.application.port.dto.DeptQueryDTO;
import com.like.system.dept.application.port.out.DeptHierarchySelectPort;

@Repository
@Transactional(readOnly = true)
public class DeptHierarchyDbAdapter implements DeptHierarchySelectPort {

	DeptJpaQueryRepository repository;
	
	public DeptHierarchyDbAdapter(DeptJpaQueryRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<DeptHierarchyResponse> select(DeptQueryDTO dto) {
		return this.repository.getDeptHierarchy(dto.organizationCode());
	}
}
