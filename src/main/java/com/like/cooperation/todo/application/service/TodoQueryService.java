package com.like.cooperation.todo.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.todo.adapter.out.persistence.TodoGroupJpaRepository;
import com.like.cooperation.todo.domain.QTodoGroup;
import com.like.cooperation.todo.domain.TodoGroup;

@Service
@Transactional(readOnly=true)
public class TodoQueryService {
	
	private TodoGroupJpaRepository repository;
	
	public TodoQueryService(TodoGroupJpaRepository repository) {
		this.repository = repository;
	}
			
	public List<TodoGroup> getTodoGroupList(String userId) {
		QTodoGroup qTodoGroup = QTodoGroup.todoGroup;
		
		//Iterable<TodoGroup> result = repository.findAll(qTodoGroup.createdBy.eq(userId)); 
		//List<TodoGroup> list = new ArrayList<>();
		//result.forEach(e -> list.add(e));
		
		return repository.findAll(qTodoGroup.userId.eq(userId));
	}
		
}
