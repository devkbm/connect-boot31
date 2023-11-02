package com.like.cooperation.todo.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.cooperation.todo.application.port.out.TodoGroupCommandDbPort;
import com.like.cooperation.todo.domain.TodoGroup;

@Repository
public class TodoGroupDbAdapter implements TodoGroupCommandDbPort {

	TodoGroupJpaRepository repository;
	
	TodoGroupDbAdapter(TodoGroupJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public TodoGroup select(Long id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public void save(TodoGroup entity) {
		this.repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);		
	}

}
