package com.like.system.dept.adapter.out.persistence.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.dept.application.port.in.dto.DeptHierarchyResponse;
import com.like.system.dept.application.port.in.dto.QDeptHierarchyResponse;
import com.like.system.dept.domain.QDept;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeptJpaQueryRepository {

	private JPAQueryFactory  queryFactory;
	private static final QDept qDept = QDept.dept;		
	
	public DeptJpaQueryRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	
	public List<DeptHierarchyResponse> getDeptHierarchy(String organizationCode) {
		List<DeptHierarchyResponse> rootNodeList = this.getDeptRootNodeList(organizationCode);
		
		List<DeptHierarchyResponse> result = this.addDeptChildNodeList(organizationCode, rootNodeList);
		
		return result;
	}
	
	private List<DeptHierarchyResponse> addDeptChildNodeList(String organizationCode, List<DeptHierarchyResponse> list) {
		List<DeptHierarchyResponse> children = null;
		
		for ( DeptHierarchyResponse node : list) {
			
			children = getDeptChildNodeList(organizationCode, node.getDeptCode());
			
			if (children.isEmpty()) {
				node.setLeaf(true);
				continue;
			} else {
				node.setChildren(children);
				node.setLeaf(false);
				
				// 재귀 호출
				this.addDeptChildNodeList(organizationCode, children);
			}			
		}
		
		return list;
	}

	private List<DeptHierarchyResponse> getDeptRootNodeList(String organizationCode) {
		return queryFactory
				.select(this.getDeptHierarchy(qDept))				
				.from(qDept)
				.where(qDept.id.organizationCode.eq(organizationCode), qDept.isRootNode())
				.orderBy(qDept.seq.asc())				
				.fetch();
	}
	
	private List<DeptHierarchyResponse> getDeptChildNodeList(String organizationCode, String parentDeptCode) {
		return queryFactory
				.select(this.getDeptHierarchy(qDept))
				.from(qDept)
				.where(qDept.id.organizationCode.eq(organizationCode), qDept.parentDept.id.deptCode.eq(parentDeptCode))
				.orderBy(qDept.seq.asc())
				.fetch();
	}
	
	private QDeptHierarchyResponse getDeptHierarchy(QDept qDept) {
		return new QDeptHierarchyResponse(qDept.parentDept.id.deptCode
										 ,qDept.id.organizationCode										 
										 ,qDept.id.deptCode
										 ,qDept.deptNameKorean
										 ,qDept.deptAbbreviationKorean
										 ,qDept.deptNameEnglish
										 ,qDept.deptAbbreviationEnglish
										 ,qDept.period
										 ,qDept.seq
										 ,qDept.comment);
	}

}
