package com.like.system.dept.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.dept.application.port.dto.DeptQueryDTO;
import com.like.system.dept.application.port.dto.DeptSaveDTO;
import com.like.system.dept.application.port.in.DeptSelectUseCase;
import com.like.system.dept.application.port.out.DeptSelectPort;

@Service
public class DeptSelectService implements DeptSelectUseCase {

	DeptSelectPort port;
	
	public DeptSelectService(DeptSelectPort port) {
		this.port = port;
	}
	
	@Override
	public DeptSaveDTO select(String organizationCode, String deptCode) {
		
		return DeptSaveDTO.toDTO(this.port.select(organizationCode, deptCode));
	}

	@Override
	public List<DeptSaveDTO> select(DeptQueryDTO dto) {
		return this.port.select(dto)
						.stream()
						.map(e -> DeptSaveDTO.toDTO(e))
						.toList();
	}
	
}
