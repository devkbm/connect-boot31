package com.like.system.term.domain;

import java.util.List;

import com.like.system.term.application.dto.TermQueryDTO;

public interface TermQueryRepository {

	List<TermDictionary> getTermList(TermQueryDTO condition);
}
