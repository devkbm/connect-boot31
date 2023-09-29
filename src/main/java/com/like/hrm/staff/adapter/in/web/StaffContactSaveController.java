package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffContactSaveDTO;
import com.like.hrm.staff.application.port.in.StaffContractSaveUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffContactSaveController {

	private StaffContractSaveUseCase useCase;
	
	public StaffContactSaveController(StaffContractSaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/hrm/staff/{staffId}/contact")
	public ResponseEntity<?> saveAppointmentRecord(@Valid @RequestBody StaffContactSaveDTO dto) {			
									
		useCase.save(dto);
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
