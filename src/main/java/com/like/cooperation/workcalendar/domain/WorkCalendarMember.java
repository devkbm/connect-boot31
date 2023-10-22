package com.like.cooperation.workcalendar.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.system.core.jpa.domain.AbstractAuditEntity;
import com.like.system.user.domain.SystemUser;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "GRWWORKCALENDARUSER")
public class WorkCalendarMember extends AbstractAuditEntity {
	
	@EmbeddedId
	WorkCalendarMemberId id;
	
	@JsonBackReference
	@MapsId("workCalendar")
	@ManyToOne
	@JoinColumn(name="ID", referencedColumnName = "ID")
	WorkCalendar workCalendar;
	
	public WorkCalendarMember(WorkCalendar workCalendar, SystemUser user) {
		this.id = new WorkCalendarMemberId(workCalendar.getId(), user.getId().getUserId()) ;
		this.workCalendar = workCalendar;
	}
	
	public String getUserId() {
		return this.getId().getUserId();
	}

	@Override
	public String toString() {
		return "WorkCalendarMember [workCalendar=" + workCalendar.id + ", user=" + this.getId().getUserId() + "]";
	}
	
	public void setWorkGroup(WorkCalendar workCalendar) {
		// 기존에 존재했던 참조 삭제
		if (this.workCalendar != null) {
			this.workCalendar.getMemberList().remove(this);
		}
		
		this.workCalendar = workCalendar;
		
		// 참조 추가
		if (workCalendar != null && !workCalendar.getMemberList().contains(this)) {
			this.workCalendar.getMemberList().add(this);
		}
		
	}
	
	
}
