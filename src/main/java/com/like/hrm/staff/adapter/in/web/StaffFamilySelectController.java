package com.like.hrm.staff.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.application.port.dto.StaffFamilySaveDTO;
import com.like.hrm.staff.application.port.in.StaffFamilySelectUseCase;
import com.like.system.core.message.MessageUtil;


@RestController
public class StaffFamilySelectController {

	private StaffFamilySelectUseCase useCase;
		
	public StaffFamilySelectController(StaffFamilySelectUseCase useCase) {
		this.useCase = useCase;	
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/family")
	public ResponseEntity<?> getFamilyList(@RequestParam String organizationCode, @PathVariable String staffId) {
													
		List<StaffFamilySaveDTO> list = useCase.select(organizationCode,staffId);
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));						
	}
	
	
	@GetMapping("/api/hrm/staff/{staffId}/family/{seq}")
	public ResponseEntity<?> getFamily(@RequestParam String organizationCode
									  ,@PathVariable String staffId
									  ,@PathVariable Long seq) {				
				
		StaffFamilySaveDTO dto =  useCase.select(organizationCode, staffId, seq);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));							
	}
}
