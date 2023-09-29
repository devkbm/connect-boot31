package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffContactSaveDTO;
import com.like.hrm.staff.application.port.in.StaffContractSelectUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffContactSelectController {

	private StaffContractSelectUseCase useCase;
	
	public StaffContactSelectController(StaffContractSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/contact")
	public ResponseEntity<?> getAppointmentRecordList(@RequestParam String organizationCode, @PathVariable String staffId) {
										
		StaffContactSaveDTO dto = useCase.select(organizationCode, staffId);										  									
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
}
