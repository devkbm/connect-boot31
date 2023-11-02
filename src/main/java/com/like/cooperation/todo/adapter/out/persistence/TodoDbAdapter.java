package com.like.cooperation.todo.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.todo.application.port.out.TodoCommandDbPort;
import com.like.cooperation.todo.domain.QTodo;
import com.like.cooperation.todo.domain.Todo;

@Repository
public class TodoDbAdapter implements TodoCommandDbPort {

	TodoJpaRepository repository;
	
	TodoDbAdapter(TodoJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Todo> selectList(Long todoGroupId) {
		return this.repository.findAll(QTodo.todo1.todoGroup.pkTodoGroup.eq(todoGroupId));
	}

	@Override
	public Todo select(Long todoId) {
		return this.repository.findById(todoId).orElse(null);
	}

	@Override
	public void save(Todo entity) {
		this.repository.save(entity);
	}

	@Override
	public void delete(Long todoId) {
		this.repository.deleteById(todoId);
	}

}
