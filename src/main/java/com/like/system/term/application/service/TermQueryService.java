package com.like.system.term.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.application.dto.TermQueryDTO;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.domain.TermQueryRepository;

@Service
@Transactional
public class TermQueryService {

	TermQueryRepository repository;

	public TermQueryService(TermQueryRepository repository) {
		this.repository = repository;
	}
	
	public List<TermDictionary> getTermList(TermQueryDTO condition) {
		return repository.getTermList(condition);
	}
}
