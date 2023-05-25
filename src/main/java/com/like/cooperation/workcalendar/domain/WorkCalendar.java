package com.like.cooperation.workcalendar.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.jpa.domain.AbstractAuditEntity;
import com.like.system.user.domain.SystemUser;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"eventList", "memberList"})
@Getter
@Entity
@Table(name = "GRWWORKCALENDAR")
@EntityListeners(AuditingEntityListener.class)
public class WorkCalendar extends AbstractAuditEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Column(name="NAME")
	String name;
	
	@Column(name="COLOR")
	String color;
	
	@OneToMany(mappedBy = "workCalendar")
	List<WorkCalendarEvent> eventList;
	
	@OrderBy("USER_ID asc")
	@OneToMany(mappedBy = "workCalendar", fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval=true)
	Set<WorkCalendarMember> memberList = new LinkedHashSet<>();
	
	public WorkCalendar(String name, String color) {		
		this.name = name;
		this.color = color;
		this.eventList = null;
		this.memberList = null;
	}
	
	public void modifyEntity(String name, String color) {
		this.name = name;
		this.color = color;
	}
	
	public void addWorkGroupMember(WorkCalendarMember member) {
		if (this.memberList == null) { 
			this.memberList = new LinkedHashSet<>();
		}		
		
		// 중복 방지
		if (!this.memberList.contains(member)) {
			this.memberList.add(member);
		}
		
		// 참조 추가
		member.setWorkGroup(this);		
	}
	
	public void deleteWorkGroupMember(SystemUser user) {		
		this.memberList.remove(new WorkCalendarMember(this, user));		
	}
	
	public void clearWorkGroupMember() {
		if (this.memberList == null) {
			this.memberList = new LinkedHashSet<>();			
		} else if (!this.memberList.isEmpty()) {
			this.memberList.clear();
		}
	}
	
}
