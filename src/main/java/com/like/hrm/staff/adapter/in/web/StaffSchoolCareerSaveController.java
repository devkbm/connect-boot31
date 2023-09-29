package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffSchoolCareerSaveDTO;
import com.like.hrm.staff.application.port.in.StaffSchoolCareerSaveUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffSchoolCareerSaveController {

	private StaffSchoolCareerSaveUseCase useCase;
		
	public StaffSchoolCareerSaveController(StaffSchoolCareerSaveUseCase useCase) {
		this.useCase = useCase;
	}
		
	@PostMapping("/api/hrm/staff/{staffId}/schoolcareer")
	public ResponseEntity<?> saveSchoolCareer(@RequestBody @Valid StaffSchoolCareerSaveDTO dto) {
		useCase.save(dto);
											 				
		return toOne(null, MessageUtil.getSaveMessage(1));
	}
	
}
