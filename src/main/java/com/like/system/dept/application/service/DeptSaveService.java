package com.like.system.dept.application.service;

import org.springframework.stereotype.Service;

import com.like.system.dept.application.port.in.DeptSaveUseCase;
import com.like.system.dept.application.port.in.dto.DeptSaveDTO;
import com.like.system.dept.application.port.out.DeptSavePort;
import com.like.system.dept.application.port.out.DeptSelectPort;
import com.like.system.dept.domain.Dept;

@Service
public class DeptSaveService implements DeptSaveUseCase {
	
	DeptSavePort port;
	DeptSelectPort selectPort;
	
	public DeptSaveService(DeptSavePort port, DeptSelectPort selectPort) {
		this.port = port;
		this.selectPort = selectPort;
	}
	
	@Override
	public void save(DeptSaveDTO dto) {
		Dept parent = selectPort.select(dto.organizationCode(), dto.parentDeptCode());
		
		this.port.save(dto.toEntity(parent));
	}

}
