package com.like.hrm.staff.application.port.in.appointment;

public interface StaffAppointmentApplyUseCase {
	void apply(String organizationCode, String staffNo, Long seq);
}
