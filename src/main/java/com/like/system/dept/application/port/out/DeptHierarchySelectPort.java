package com.like.system.dept.application.port.out;

import java.util.List;

import com.like.system.dept.application.port.dto.DeptHierarchyResponse;
import com.like.system.dept.application.port.dto.DeptQueryDTO;

public interface DeptHierarchySelectPort {
	List<DeptHierarchyResponse> select(DeptQueryDTO dto);
}
