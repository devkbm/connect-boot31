package com.like.hrm.staff.adapter.in.web.schoolcareer;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffSchoolCareerSaveDTO;
import com.like.hrm.staff.application.port.in.schoolcareer.StaffSchoolCareerQueryUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffSchoolCareerQueryController {
	
	StaffSchoolCareerQueryUseCase useCase;
	
	StaffSchoolCareerQueryController(StaffSchoolCareerQueryUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/schoolcareer")
	public ResponseEntity<?> getSchoolCareer(@RequestParam String organizationCode, @PathVariable String staffId) {			
		List<StaffSchoolCareerSaveDTO> list = useCase.select(organizationCode, staffId);
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
