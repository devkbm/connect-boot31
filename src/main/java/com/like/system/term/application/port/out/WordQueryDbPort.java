package com.like.system.term.application.port.out;

import java.util.List;

import com.like.system.term.domain.WordDictionary;

public interface WordQueryDbPort {
	List<WordDictionary> select();
}
