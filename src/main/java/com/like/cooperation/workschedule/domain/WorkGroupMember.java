package com.like.cooperation.workschedule.domain;

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
@IdClass(WorkGroupMemberId.class)
@Table(name = "GRWWORKGROUPUSER")
public class WorkGroupMember extends AbstractAuditEntity {
	
	@JsonBackReference
	@Id	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKGROUP_ID")
	WorkGroup workGroup;
	
	@JsonBackReference
	@Id	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	SystemUser user;
	
	public WorkGroupMember(WorkGroup workGroup, SystemUser user) {
		this.workGroup = workGroup;
		this.user = user;
	}
	
	public String getUserId() {
		return this.user.getId();
	}

	@Override
	public String toString() {
		return "WorkGroupMember [workGroup=" + workGroup.id + ", user=" + user.getId() + "]";
	}
	
	public void setWorkGroup(WorkGroup workGroup) {
		// 기존에 존재했던 참조 삭제
		if (this.workGroup != null) {
			this.workGroup.getMemberList().remove(this);
		}
		
		this.workGroup = workGroup;
		
		// 참조 추가
		if (workGroup != null && !workGroup.getMemberList().contains(this)) {
			this.workGroup.getMemberList().add(this);
		}
		
	}
	
	
}
