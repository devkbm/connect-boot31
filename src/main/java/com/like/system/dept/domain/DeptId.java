package com.like.system.dept.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DeptId implements Serializable {
	
	private static final long serialVersionUID = 5869853950528008537L;

	@Column(name="ORG_CD")
	private String organizationCode;
	
	@Column(name = "DEPT_CD", nullable = false)
	private String deptCode;
	
	protected DeptId() {}
	
	public DeptId(String organizationCode, String deptCode) {
		this.organizationCode = organizationCode;
		this.deptCode = deptCode;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public String getDeptCode() {
		return deptCode;
	}
}
