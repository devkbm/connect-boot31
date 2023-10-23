package com.like.system.webresource.application.port.out;

import com.like.system.webresource.domain.WebResource;

public interface WebResourceDbSavePort {
	void save(WebResource entity);
}
