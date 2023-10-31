package com.like.system.term.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.system.term.domain.TermDictionary;

@Repository
public interface TermDictionaryJpaRepository extends JpaRepository<TermDictionary, String> {
		
}
