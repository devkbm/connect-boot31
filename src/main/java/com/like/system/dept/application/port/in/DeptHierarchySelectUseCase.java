package com.like.system.dept.application.port.in;

import java.util.List;

import com.like.system.dept.application.port.in.dto.DeptQueryConditionDTO;
import com.like.system.dept.application.port.in.dto.DeptHierarchyResponse;

public interface DeptHierarchySelectUseCase {

	List<DeptHierarchyResponse> select(DeptQueryConditionDTO dto);
}
