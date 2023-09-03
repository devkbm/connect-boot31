package com.like.system.webresource.application.port.out;

import com.like.system.webresource.application.port.in.dto.WebResourceSaveDTO;

public interface WebResourceDbSelectPort {

	WebResourceSaveDTO select(String webResourceId);
}
