package com.like.cooperation.todo.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.todo.application.port.in.TodoGroupCreateUseCase;
import com.like.cooperation.todo.application.port.out.TodoGroupCommandDbPort;
import com.like.cooperation.todo.domain.TodoGroup;

@Transactional
@Service
public class TodoGroupCreateService implements TodoGroupCreateUseCase {

	TodoGroupCommandDbPort dbPort;
	
	TodoGroupCreateService(TodoGroupCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public TodoGroup create(String userId) {
		TodoGroup taskGroup = new TodoGroup(userId);
		dbPort.save(taskGroup);
		
		return taskGroup;
	}

}
