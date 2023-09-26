package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.ResponseStaff;
import com.like.hrm.staff.application.port.in.StaffSelectUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffSelectController {
	
	private StaffSelectUseCase useCase;
		
	public StaffSelectController(StaffSelectUseCase useCase) {
		this.useCase = useCase;
	}		
	
	@GetMapping("/api/hrm/staff/{id}")
	public ResponseEntity<?> getStaff(@RequestParam String organizationCode, @PathVariable String id) {
								
		ResponseStaff dto = useCase.select(organizationCode, id); 
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}		
			
}
