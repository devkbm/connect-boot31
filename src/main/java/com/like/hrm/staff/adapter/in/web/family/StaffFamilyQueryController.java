package com.like.hrm.staff.adapter.in.web.family;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffFamilySaveDTO;
import com.like.hrm.staff.application.port.in.family.StaffFamilyQueryUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffFamilyQueryController {

	private StaffFamilyQueryUseCase useCase;
	
	public StaffFamilyQueryController(StaffFamilyQueryUseCase useCase) {
		this.useCase = useCase;	
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/family")
	public ResponseEntity<?> getFamilyList(@RequestParam String organizationCode, @PathVariable String staffId) {
													
		List<StaffFamilySaveDTO> list = useCase.select(organizationCode,staffId);
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));						
	}
}
