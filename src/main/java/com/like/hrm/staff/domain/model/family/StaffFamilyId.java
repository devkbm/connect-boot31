package com.like.hrm.staff.domain.model.family;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import com.like.hrm.staff.domain.model.Staff;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StaffFamilyId implements Serializable {
	
	private static final long serialVersionUID = -8748836639137047169L;
	
	@Column(name="ORG_CD")
	String organizationCode;
		
	@Column(name="STAFF_NO")
	String staffNo;
		
	@Comment("등록순번")
	@Column(name="SEQ")
	Long seq;		
	
	public StaffFamilyId(Staff staff, Long seq) {
		this.organizationCode = staff.getId().getOrganizationCode();
		this.staffNo = staff.getId().getStaffNo();
		this.seq = seq;
	}	
	
}
