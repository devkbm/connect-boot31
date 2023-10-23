package com.like.system.dept.application.port.in;

import java.util.List;

import com.like.system.dept.application.port.dto.DeptQueryDTO;
import com.like.system.dept.application.port.dto.DeptSaveDTO;

public interface DeptSelectUseCase {

	DeptSaveDTO select(String organizationCode, String deptCode);
	
	List<DeptSaveDTO> select(DeptQueryDTO dto);
}
