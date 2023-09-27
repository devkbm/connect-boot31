package com.like.hrm.anualleave.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import com.like.hrm.staff.domain.model.Staff;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"organizationCode","staffNo","yyyy"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AnualLeaveId implements Serializable {
	
	private static final long serialVersionUID = 7192154823642621593L;

	@Column(name="ORG_CD")
	String organizationCode;
	
	@Comment("직원번호")
	@Column(name="STAFF_NO")
	String staffNo;
	
	@Comment("귀속년도")
	@Column(name="YYYY")
	Integer yyyy;			
	
	public AnualLeaveId(Staff staff, Integer yyyy) {
		this.organizationCode = staff.getId().getOrganizationCode();
		this.staffNo = staff.getId().getStaffNo();
		this.yyyy = yyyy;
	}
}
