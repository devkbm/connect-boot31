package com.like.system.dept.adapter.out.persistence.jpa.repository;

import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.QDept;
import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;

public class DeptQueryExpression {

	@QueryDelegate(Dept.class)
	public static BooleanExpression isRootNode(QDept dept) {
		return dept.parentDeptCode.isNull();
	}
	
	@QueryDelegate(Dept.class)
	public static BooleanExpression isChildNode(QDept dept) {
		return dept.parentDeptCode.isNotNull();
	}
}
