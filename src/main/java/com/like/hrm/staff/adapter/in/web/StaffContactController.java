package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffDTO;
import com.like.hrm.staff.application.service.StaffContactService;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffContactController {

	private StaffContactService service;
	
	public StaffContactController(StaffContactService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/contact")
	public ResponseEntity<?> getAppointmentRecordList(@RequestParam String organizationCode, @PathVariable String staffId) {
										
		StaffDTO.FormContact dto = service.get(organizationCode, staffId);										  									
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
	@PostMapping("/api/hrm/staff/{staffId}/contact")
	public ResponseEntity<?> saveAppointmentRecord(@Valid @RequestBody StaffDTO.FormContact dto) {			
									
		service.save(dto);
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
