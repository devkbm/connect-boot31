package com.like.system.webresource.application.port.in;

import java.util.List;

import com.like.system.webresource.application.port.in.dto.WebResourceQueryConditionDTO;
import com.like.system.webresource.application.port.in.dto.WebResourceSaveDTO;

public interface WebResourceQueryUseCase {

	List<WebResourceSaveDTO> getResourceList(WebResourceQueryConditionDTO condition);
}
