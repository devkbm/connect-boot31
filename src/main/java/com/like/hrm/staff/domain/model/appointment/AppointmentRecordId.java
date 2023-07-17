package com.like.hrm.staff.domain.model.appointment;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import com.like.hrm.staff.domain.model.Staff;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AppointmentRecordId implements Serializable {
	
	private static final long serialVersionUID = -9052607243196727987L;

	@Column(name="ORG_CD")
	String organizationCode;
		
	@Column(name="STAFF_NO")
	String staffNo;
	
	@Comment("등록순번")
	@Column(name="SEQ")
	Long seq;
	
	public AppointmentRecordId(Staff staff, Long seq) {
		this.organizationCode = staff.getId().getOrganizationCode();
		this.staffNo = staff.getId().getStaffNo();
		this.seq = seq;
	}
}
