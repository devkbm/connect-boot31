package com.like.cooperation.workcalendar.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@IdClass(WorkCalendarMemberId.class)
@Table(name = "GRWWORKCALENDARUSER")
public class WorkCalendarMember extends AbstractAuditEntity {
	
	@JsonBackReference
	@Id	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKCALENDAR_ID")
	WorkCalendar workCalendar;
	
	@JsonBackReference
	@Id	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	SystemUser user;
	
	public WorkCalendarMember(WorkCalendar workCalendar, SystemUser user) {
		this.workCalendar = workCalendar;
		this.user = user;
	}
	
	public String getUserId() {
		return this.user.getId();
	}

	@Override
	public String toString() {
		return "WorkCalendarMember [workCalendar=" + workCalendar.id + ", user=" + user.getId() + "]";
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
