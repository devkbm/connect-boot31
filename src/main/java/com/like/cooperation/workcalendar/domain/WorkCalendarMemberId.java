package com.like.cooperation.workcalendar.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@EqualsAndHashCode
@NoArgsConstructor
public class WorkCalendarMemberId implements Serializable {
		
	private static final long serialVersionUID = -9015996959356053573L;

	Long workCalendar;
			
	String user;		

	public WorkCalendarMemberId(Long workCalendar, String userId) {		
		this.workCalendar = workCalendar;
		this.user = userId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkCalendarMemberId other = (WorkCalendarMemberId) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (workCalendar == null) {
			if (other.workCalendar != null)
				return false;
		} else if (!workCalendar.equals(other.workCalendar))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((workCalendar == null) ? 0 : workCalendar.hashCode());
		return result;
	}
	
	
}
