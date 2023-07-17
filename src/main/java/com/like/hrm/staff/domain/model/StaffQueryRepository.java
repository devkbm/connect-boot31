package com.like.hrm.staff.domain.model;

import java.util.List;

import com.like.hrm.staff.boundary.StaffDTO.SearchStaff;
import com.like.hrm.staff.boundary.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.boundary.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.boundary.ResponseStaffDutyResponsibility;

public interface StaffQueryRepository {

	
	List<Staff> getStaffList(SearchStaff dto);
	
	ResponseStaffCurrentAppointment getStaffCurrentAppointment(String organizationCode, String staffNo);
		
	List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String organizationCode, String staffNo);
	
	List<ResponseStaffDutyResponsibility> getStaffDutyResponsibility(String organizationCode, String staffNo);
	
}
