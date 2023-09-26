package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.service.StaffAppointmentService;

@RestController
public class StaffAppointmentApplyController {

	private StaffAppointmentService service;
	
	public StaffAppointmentApplyController(StaffAppointmentService service) {
		this.service = service;
	}	
	
	@GetMapping("/api/hrm/staff/{staffId}/appointmentrecord/{id}/apply")
	//@RequestMapping(value={"/hrm/staff/{staffId}/appointmentrecord/{id}/apply"}, method={RequestMethod.POST})	
	public ResponseEntity<?> applyAppointmentRecord(@RequestParam String organizationCode
												   ,@PathVariable String staffId
 			 									   ,@PathVariable Long id) {									
						
		service.applyAppointmentRecord(organizationCode, staffId, id);
											 				
		return toList(null, "발령처리되었습니다.");
	}
}
