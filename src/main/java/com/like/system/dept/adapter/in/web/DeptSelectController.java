package com.like.system.dept.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.dept.application.port.in.DeptSelectUseCase;
import com.like.system.dept.application.port.in.dto.DeptQueryConditionDTO;
import com.like.system.dept.application.port.in.dto.DeptSaveDTO;

@RestController
public class DeptSelectController {
	
	private DeptSelectUseCase useCase;	
	
	public DeptSelectController(DeptSelectUseCase useCase) {
		this.useCase = useCase;		
	}
		
	@GetMapping("/api/system/dept/{deptCode}")
	public ResponseEntity<?> getDept(@RequestParam String organizationCode, @PathVariable String deptCode) {									 
		
		DeptSaveDTO dto = useCase.select(organizationCode, deptCode);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}		

	@GetMapping("/api/system/dept")
	public ResponseEntity<?> getDeptList(@ModelAttribute DeptQueryConditionDTO dto) {
							
		List<DeptSaveDTO> list = useCase.select(dto);  						 						
		
		return toList(list, String.format("%d 건 조회되었습니다.", list.size()));
	}
	
}
