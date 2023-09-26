package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;
import com.like.hrm.staff.application.service.StaffAppointmentService;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffAppointmentSelectController {

	private StaffAppointmentService service;
	
	public StaffAppointmentSelectController(StaffAppointmentService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/appointmentrecord")
	public ResponseEntity<?> getAppointmentRecordList(@RequestParam String organizationCode, @PathVariable String staffId) {
										
		List<StaffAppointmentRecordDTO> list = service.getAppointmentRecord(organizationCode, staffId)
																			.getStream()
																			.map(e -> StaffAppointmentRecordDTO.convert(e))
																			.toList(); 		
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/appointmentrecord/{id}")
	public ResponseEntity<?> getAppointmentRecord(@RequestParam String organizationCode
												 ,@PathVariable String staffId
									  			 ,@PathVariable Long id) {
				
		AppointmentRecord entity = service.getAppointmentRecord(organizationCode, staffId, id);  									
				
		var dto = StaffAppointmentRecordDTO.convert(entity) ;
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
}
