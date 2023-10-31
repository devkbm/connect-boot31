package com.like.hrm.staff.application.port.out;

import java.util.List;

import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;

public interface StaffAppointmentQueryDbPort {
	List<AppointmentRecord> select(String organizationCode, String staffNo);
}
