package com.like.hrm.staff.domain.model.dutyresponsibility;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import com.like.hrm.staff.domain.model.Staff;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StaffDutyId implements Serializable {
	
	private static final long serialVersionUID = -1176225352272330423L;
	
	@Column(name="ORG_CD")
	String organizationCode;
		
	@Column(name="STAFF_NO")
	String staffNo;
		
	@Comment("등록순번")
	@Column(name="SEQ")
	Long seq;
	
	public StaffDutyId(Staff staff, Long seq) {
		this.organizationCode = staff.getId().getOrganizationCode();
		this.staffNo = staff.getId().getStaffNo();
		this.seq = seq;
	}
}
