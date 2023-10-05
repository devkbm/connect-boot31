package com.like.hrm.workchangeapp.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMDUTYAPPLICATIONDATE")
public class DutyApplicationDate extends AbstractAuditEntity {
	
	@EmbeddedId
	private DutyApplicationDateId id;	
	
	@Column(name="DUTY_TIME", nullable = false)
	private BigDecimal dutyTime;
	
	public DutyApplicationDate(DutyApplication dutyApplication
							  ,LocalDate date
							  ,BigDecimal dutyTime) {
		this.id = new DutyApplicationDateId(dutyApplication, date);
		this.dutyTime = dutyTime;
	}
	
}
