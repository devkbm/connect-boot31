package com.like.hrm.staff.application.port.dto;

import jakarta.validation.constraints.NotEmpty;

public record StaffCreateDTO(
		String clientAppUrl,
		String organizationCode,			
		@NotEmpty(message = "직원번호는 필수 입력 값입니다.")
		String staffNo,
		@NotEmpty(message = "이름은 필수 입력 값입니다.")
		String name,
		@NotEmpty(message = "주민등록번호는 필수 입력 값입니다.")
		String residentRegistrationNumber,
		String nameEng,
		String nameChi			
		) {	
	
	public String getStaffId() {
		return this.organizationCode + "_" + this.staffNo;
	}
	
}