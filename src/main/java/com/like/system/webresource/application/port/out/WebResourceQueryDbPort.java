package com.like.system.webresource.application.port.out;

import java.util.List;

import com.like.system.webresource.application.port.dto.WebResourceQueryDTO;
import com.like.system.webresource.domain.WebResource;

public interface WebResourceQueryDbPort {
	List<WebResource> getResourceList(WebResourceQueryDTO condition);
}
