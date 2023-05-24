package com.like.system.term.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermDictionaryRepository extends JpaRepository<TermDictionary, String> {
		
}
