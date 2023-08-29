package com.like.system.dept.application.port.out;

import java.util.List;

import com.like.system.dept.application.port.in.dto.DeptQueryConditionDTO;
import com.like.system.dept.application.port.in.dto.DeptSaveDTO;
import com.like.system.dept.domain.Dept;

public interface DeptSelectPort {

	Dept select(String organizationCode, String deptCode);
	
	DeptSaveDTO selectDTO(String organizationCode, String deptCode);
	
	List<DeptSaveDTO> select(DeptQueryConditionDTO dto);
}
