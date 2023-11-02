package com.like.cooperation.todo.application.port.out;

import java.util.List;

import com.like.cooperation.todo.domain.Todo;

public interface TodoCommandDbPort {

	List<Todo> selectList(Long todoGroupId);
	
	Todo select(Long todoId);
	
	void save(Todo dto);
	
	void delete(Long todoId);
}
