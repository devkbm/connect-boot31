package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.FormStaff;
import com.like.hrm.staff.application.port.dto.NewStaff;
import com.like.hrm.staff.application.port.dto.ResponseStaff;
import com.like.hrm.staff.application.service.StaffService;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffController {
	
	private StaffService staffService;
		
	public StaffController(StaffService staffService) {
		this.staffService = staffService;
	}		
	
	@GetMapping("/api/hrm/staff/{id}")
	public ResponseEntity<?> getStaff(@RequestParam String organizationCode, @PathVariable String id) {
								
		ResponseStaff dto = ResponseStaff.toDTO(staffService.getStaff(organizationCode, id)); 
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/staff/create")
	public ResponseEntity<?> newStaff(@RequestBody @Valid NewStaff dto) {											
								
		staffService.newStaff(dto);
											 				
		return toList(null, "직원번호 : %s , 생성되었습니다.".formatted(dto.staffNo()));
	}
		
	@PostMapping("/api/hrm/staff")
	public ResponseEntity<?> saveStaff(@RequestBody @Valid FormStaff dto) {			
														
		staffService.saveStaff(dto);
											 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/hrm/staff/{id}")
	public ResponseEntity<?> deleteStaff(@RequestParam String organizationCode, @PathVariable String id) {
								
		staffService.deleteStaff(organizationCode, id); 
		
		return toOne(null,"직원번호 : %s , 삭제되었습니다.".formatted(id));
	}
			
}
