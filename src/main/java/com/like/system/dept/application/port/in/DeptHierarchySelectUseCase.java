package com.like.system.dept.application.port.in;

import java.util.List;

import com.like.system.dept.application.port.dto.DeptHierarchyResponse;
import com.like.system.dept.application.port.dto.DeptQueryDTO;

public interface DeptHierarchySelectUseCase {

	List<DeptHierarchyResponse> select(DeptQueryDTO dto);
}
