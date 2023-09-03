package com.like.system.webresource.application.port.in;

import com.like.system.webresource.application.port.in.dto.WebResourceSaveDTO;

public interface WebResourceSelectUseCase {

	WebResourceSaveDTO select(String webResourceId);
}
