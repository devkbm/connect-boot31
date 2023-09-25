package com.like.hrm.staff.application.port.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffName;

import jakarta.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FormStaff(
		String organizationCode,
		@NotEmpty(message = "직원번호는 필수 입력 값입니다.")
		String staffNo,
		String name,
		String nameEng,
		String nameChi,
		String gender,
		LocalDate birthday
		) {
	
	public void modifyEntity(Staff entity) {
		entity.modifyEntity(new StaffName(name, nameEng, nameChi)
				 		   ,this.birthday);
	}
}