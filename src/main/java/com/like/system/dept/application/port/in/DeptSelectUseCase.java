package com.like.system.dept.application.port.in;

import java.util.List;

import com.like.system.dept.application.port.in.dto.DeptQueryConditionDTO;
import com.like.system.dept.application.port.in.dto.DeptSaveDTO;

public interface DeptSelectUseCase {

	DeptSaveDTO select(String organizationCode, String deptCode);
	
	List<DeptSaveDTO> select(DeptQueryConditionDTO dto);
}
