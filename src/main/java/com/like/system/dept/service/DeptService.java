package com.like.system.dept.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.dept.boundary.DeptDTO;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptId;
import com.like.system.dept.domain.DeptRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class DeptService {

	private DeptRepository deptRepository;
	
	public DeptService(DeptRepository deptRepository) {
		this.deptRepository = deptRepository;
	}

	public boolean isDept(String organizationCode, String deptCode) {
		return deptRepository.existsById(new DeptId(organizationCode, deptCode));
	}
	
	public Dept getDept(String organizationCode, String deptCode) {
		return deptRepository.findById(new DeptId(organizationCode, deptCode)).orElse(null);
	}	
	
	public void createDept(Dept dept) {
		deptRepository.save(dept);
	}			
	
	public void saveDept(Dept dept) {				
		deptRepository.save(dept);
	}
	
	public void saveDept(DeptDTO.FormDept dto) {
		Dept dept = dto.deptCode() == null ? null : deptRepository.findById(new DeptId(dto.organizationCode(), dto.deptCode())).orElse(null);
		Dept parentDept = dto.parentDeptCode() == null ? null : deptRepository.findById(new DeptId(dto.organizationCode(), dto.parentDeptCode())).orElse(null); 			
		
		if (dept == null) {
			dept = dto.newDept(parentDept);
		} else {
			dto.modifyDept(dept, parentDept);
		}				
		log.info(dept.toString());
		
		deptRepository.save(dept);
	}
	
	public void deleteDept(String organizationCode, String deptCode) {
		deptRepository.deleteById(new DeptId(organizationCode, deptCode));
	}
}
