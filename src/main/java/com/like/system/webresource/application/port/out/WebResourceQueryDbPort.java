package com.like.system.webresource.application.port.out;

import java.util.List;

import com.like.system.webresource.application.port.in.dto.WebResourceQueryConditionDTO;
import com.like.system.webresource.application.port.in.dto.WebResourceSaveDTO;

public interface WebResourceQueryDbPort {
	List<WebResourceSaveDTO> getResourceList(WebResourceQueryConditionDTO condition);
}
