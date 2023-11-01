package com.like.system.webresource.application.port.out;

import com.like.system.webresource.domain.WebResource;

public interface WebResourceCommandDbPort {
	WebResource select(String id);
	
	void save(WebResource entity);
	
	void delete(String id);
}
