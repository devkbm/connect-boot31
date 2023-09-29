package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffLicenseSaveDTO;
import com.like.hrm.staff.application.port.in.StaffLicenseSelectUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffLicenseSelectController {

	private StaffLicenseSelectUseCase useCase;
		
	public StaffLicenseSelectController(StaffLicenseSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/license")
	public ResponseEntity<?> getLicense(@RequestParam String organizationCode, @PathVariable String staffId) {
						
		List<StaffLicenseSaveDTO> list = useCase.select(organizationCode, staffId);
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/license/{id}")
	public ResponseEntity<?> getLicense(@RequestParam String organizationCode
									   ,@PathVariable String staffId
									   ,@PathVariable Long id) {
		
		StaffLicenseSaveDTO dto = useCase.select(organizationCode, staffId, id); 
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	
}
