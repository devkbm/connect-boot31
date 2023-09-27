package com.like.hrm.staff.domain.model.schoolcareer;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StaffSchoolCareerId implements Serializable {
		
	private static final long serialVersionUID = -752236787842572663L;

	@Column(name="ORG_CD")
	String organizationCode;
		
	@Column(name="STAFF_NO")
	String staffNo;
		
	@Comment("등록순번")
	@Column(name="SEQ")
	Long seq;
	
	public StaffSchoolCareerId(Staff staff, Long seq) {
		this.organizationCode = staff.getId().getOrganizationCode();
		this.staffNo = staff.getId().getStaffNo();
		this.seq = seq;
	}
}
