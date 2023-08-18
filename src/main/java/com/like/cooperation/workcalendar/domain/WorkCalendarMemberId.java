package com.like.cooperation.workcalendar.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class WorkCalendarMemberId implements Serializable {
		
	private static final long serialVersionUID = -9015996959356053573L;

	Long workCalendar;
			
	@Column(name="USER_ID")
	String userId;	
	
	public WorkCalendarMemberId(Long workCalendar, String userId) {
		this.workCalendar = workCalendar;
		this.userId = userId;
	}
		
}
