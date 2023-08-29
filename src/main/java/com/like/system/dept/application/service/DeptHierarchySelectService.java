package com.like.system.dept.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.dept.application.port.in.DeptHierarchySelectUseCase;
import com.like.system.dept.application.port.in.dto.DeptQueryConditionDTO;
import com.like.system.dept.application.port.in.dto.DeptHierarchyResponse;
import com.like.system.dept.application.port.out.DeptHierarchySelectPort;

@Service
public class DeptHierarchySelectService implements DeptHierarchySelectUseCase {

	DeptHierarchySelectPort port;
	
	DeptHierarchySelectService(DeptHierarchySelectPort port) {
		this.port = port;
	}
	
	@Override
	public List<DeptHierarchyResponse> select(DeptQueryConditionDTO dto) {		
		return this.port.select(dto);
	}

}
