package com.like.system.dept.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
//import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import com.like.system.core.jpa.domain.AbstractAuditEntity;
import com.like.system.core.jpa.vo.LocalDatePeriod;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true, includeFieldNames = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "internalBuilder")
@Getter
@Entity
@Table(name = "comdept")
@EntityListeners(AuditingEntityListener.class)
public class Dept extends AbstractAuditEntity implements Serializable {

	private static final long serialVersionUID = -969524977226888898L;

	/*
	@Id
	@Column(name = "DEPT_ID", nullable = false)
	String deptId;
	
	@Column(name = "ORG_CD", nullable = false)
	String organizationCode;
	
	@Column(name = "DEPT_CD", nullable = false)
	String deptCode;
	*/
	
	@EmbeddedId
	DeptId id;

	@Column(name = "DEPT_NM_KOR")
	String deptNameKorean;

	@Column(name = "DEPT_ABBR_KOR")
	String deptAbbreviationKorean;

	@Column(name = "DEPT_NM_ENG")
	String deptNameEnglish;

	@Column(name = "DEPT_ABBR_ENG")
	String deptAbbreviationEnglish;
	
	@Embedded
	LocalDatePeriod period;
	
	@Builder.Default
	@Column(name="PRT_SEQ")
	Integer seq = 0;
	
	@Column(name = "CMT")
	String comment;
	
	@Column(name = "P_DEPT_CD")
	String parentDeptCode;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	//@JoinColumn(name = "P_DEPT_ID", nullable = true)
	@JoinColumns({
		@JoinColumn(name = "ORG_CD", referencedColumnName = "ORG_CD", insertable = false, updatable = false),
		@JoinColumn(name = "P_DEPT_CD", referencedColumnName = "DEPT_CD", insertable = false, updatable = false)
	})	
	Dept parentDept;
	
	public static DeptBuilder builder(String organizationCode, String deptCode) {
		Assert.hasText(organizationCode, "organizationCode must not be empty!");
		Assert.hasText(deptCode, "deptCode must not be empty!");
		
		/*
		return internalBuilder().id(organizationCode + deptCode)
								.organizationCode(organizationCode)
								.deptCode(deptCode);
		*/
		
		return internalBuilder().id(new DeptId(organizationCode, deptCode));
	}	
		
	public Dept getParentDept() {
		return parentDept;
	}
}
