package com.like.cooperation.todo.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.todo.application.port.in.TodoDeleteUseCase;
import com.like.cooperation.todo.application.port.out.TodoGroupCommandDbPort;
import com.like.cooperation.todo.domain.TodoGroup;

@Transactional
@Service
public class TodoDeleteService implements TodoDeleteUseCase {

	TodoGroupCommandDbPort dbPort;
	
	TodoDeleteService(TodoGroupCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}

	@Override
	public void delete(Long todoGroupId, Long todoId) {
		TodoGroup todoGroup = dbPort.select(todoGroupId);	
		todoGroup.removeTodo(todoId);
		dbPort.save(todoGroup);
	}
}
