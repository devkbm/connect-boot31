package com.like.system.term.application.port.out;

import java.util.List;

import com.like.system.term.domain.DataDomainDictionary;

public interface DataDomainQueryDbPort {
	List<DataDomainDictionary> select();
}
