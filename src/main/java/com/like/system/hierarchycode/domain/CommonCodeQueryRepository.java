package com.like.system.hierarchycode.domain;

import java.util.List;

import com.like.system.hierarchycode.application.port.in.dto.CodeComboDTO;
import com.like.system.hierarchycode.application.port.in.dto.CodeDTO;
import com.like.system.hierarchycode.application.port.in.dto.CodeHierarchy;
import com.querydsl.core.types.Predicate;

public interface CommonCodeQueryRepository {

	List<Code> getCodeList(String parentCodeId);
	
	List<Code> getCodeList(Predicate predicate);
	
	List<CodeHierarchy> getCodeHierarchyList(CodeDTO.Search dto);
	
	List<CodeComboDTO> getCodeListByComboBox(String codeGroup);
}
