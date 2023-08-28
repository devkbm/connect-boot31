package com.like.system.biztypecode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.biztypecode.adapter.out.persistence.jpaentity.BizTypeEnum;
import com.like.system.biztypecode.application.port.in.dto.BizCodeTypeDTO;
import com.like.system.biztypecode.application.service.BizCodeTypeService;
import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.core.dto.HtmlSelectOptionable;
import com.like.system.core.message.MessageUtil;

@RestController
public class BizCodeTypeQueryController {

	private BizCodeTypeService service;
	
	public BizCodeTypeQueryController(BizCodeTypeService service) {
		this.service = service;
	}
	
	@GetMapping("/api/system/bizcodetype/system")
	public ResponseEntity<?> getSystemList() {				
		
		List<HtmlSelectOptionRecord> list = HtmlSelectOptionable.fromEnum(BizTypeEnum.class);			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/system/bizcodetype")
	public ResponseEntity<?> getBizCodeTypeList() {
		
		List<BizCodeTypeDTO.Form> list = service.getBizCodeTypeAllList()
											   .stream()
											   .map(e -> BizCodeTypeDTO.Form.convert(e))
											   .toList();											   
					
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
