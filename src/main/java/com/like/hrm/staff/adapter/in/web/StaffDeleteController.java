package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.in.StaffDeleteUseCase;

@RestController
public class StaffDeleteController {
	
	private StaffDeleteUseCase useCase;
		
	public StaffDeleteController(StaffDeleteUseCase useCase) {
		this.useCase = useCase;
	}			
	
	@DeleteMapping("/api/hrm/staff/{id}")
	public ResponseEntity<?> deleteStaff(@RequestParam String organizationCode, @PathVariable String id) {
								
		useCase.delete(organizationCode, id); 
		
		return toOne(null,"직원번호 : %s , 삭제되었습니다.".formatted(id));
	}
			
}
