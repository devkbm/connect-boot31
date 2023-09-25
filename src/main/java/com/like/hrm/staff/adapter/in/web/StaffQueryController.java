package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.ResponseStaff;
import com.like.hrm.staff.application.port.dto.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.application.port.dto.SearchStaff;
import com.like.hrm.staff.application.service.StaffQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffQueryController {
	
	private StaffQueryService service;
	
	public StaffQueryController(StaffQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/api/hrm/staff")
	public ResponseEntity<?> getStaffList(SearchStaff dto) {
									
		List<ResponseStaff> list = service.getStaff(dto)
												   .stream()
												   .map(e -> ResponseStaff.convert(e))
												   .toList(); 
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{id}/record")
	public ResponseEntity<?> getStaffAppointmentRecordList(@RequestParam String organizationCode, @PathVariable String id) {
		
		List<?> list = service.getStaffAppointmentRecordList(organizationCode, id);								
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{id}/currentappointment")
	public ResponseEntity<?> getStaffCurrentAppointment(@RequestParam String organizationCode, @PathVariable String id) {
		
		ResponseStaffCurrentAppointment dto = service.getStaffCurrentAppointment(organizationCode, id);								
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
}
