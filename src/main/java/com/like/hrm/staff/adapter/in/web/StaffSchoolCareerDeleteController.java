package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.in.StaffSchoolCareerDeleteUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffSchoolCareerDeleteController {

	private StaffSchoolCareerDeleteUseCase useCase;
		
	public StaffSchoolCareerDeleteController(StaffSchoolCareerDeleteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@DeleteMapping("/api/hrm/staff/{staffId}/schoolcareer/{seq}")
	public ResponseEntity<?> deleteSchoolCareer(@RequestParam String organizationCode
											   ,@PathVariable String staffId
											   ,@PathVariable Long seq) {
		useCase.delete(organizationCode, staffId, seq);
											 				
		return toOne(null, MessageUtil.getDeleteMessage(1));
	}
	
}
