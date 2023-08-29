package com.like.system.dept.application.port.out;

import java.util.List;

import com.like.system.dept.application.port.in.dto.DeptQueryConditionDTO;
import com.like.system.dept.application.port.in.dto.DeptHierarchyResponse;

public interface DeptHierarchySelectPort {
	List<DeptHierarchyResponse> select(DeptQueryConditionDTO dto);
}
