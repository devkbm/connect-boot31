package com.like.system.webresource.application.port.in;

import java.util.List;

import com.like.system.webresource.application.port.dto.WebResourceQueryDTO;
import com.like.system.webresource.application.port.dto.WebResourceSaveDTO;

public interface WebResourceQueryUseCase {

	List<WebResourceSaveDTO> getResourceList(WebResourceQueryDTO condition);
}
