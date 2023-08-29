package com.like.system.dept.application.service;

import org.springframework.stereotype.Service;

import com.like.system.dept.application.port.in.DeptDeleteUseCase;
import com.like.system.dept.application.port.out.DeptDeletePort;

@Service
public class DeptDeleteService implements DeptDeleteUseCase {

	DeptDeletePort port;
	
	DeptDeleteService(DeptDeletePort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String organizationCode, String deptCode) {
		this.port.delete(organizationCode, deptCode);
	}

}
