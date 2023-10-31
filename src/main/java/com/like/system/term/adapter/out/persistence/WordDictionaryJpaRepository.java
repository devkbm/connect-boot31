package com.like.system.term.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.system.term.domain.WordDictionary;

@Repository
public interface WordDictionaryJpaRepository extends JpaRepository<WordDictionary, String> {
		
}
