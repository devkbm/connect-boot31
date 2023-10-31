package com.like.system.term.application.port.out;

import java.util.List;

import com.like.system.term.application.dto.TermQueryDTO;
import com.like.system.term.domain.TermDictionary;

public interface TermQueryDbPort {
	List<TermDictionary> select(TermQueryDTO dto);
}
