package com.like.system.webresource.application.port.out;

import com.like.system.webresource.application.port.in.dto.WebResourceSaveDTO;

public interface WebResourceDbSavePort {
	void save(WebResourceSaveDTO dto);
}
