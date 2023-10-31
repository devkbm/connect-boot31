package com.like.hrm.staff.adapter.in.web.appointment;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;
import com.like.hrm.staff.application.port.in.appointment.StaffAppointmentSaveUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffAppointmentSaveController {

	private StaffAppointmentSaveUseCase useCase;
	
	public StaffAppointmentSaveController(StaffAppointmentSaveUseCase useCase) {
		this.useCase = useCase;
	}
		
		
	@PostMapping("/api/hrm/staff/{staffId}/appointmentrecord")
	public ResponseEntity<?> saveAppointmentRecord(@Valid @RequestBody StaffAppointmentRecordDTO dto) {			
									
		useCase.save(dto);
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}
		
}
