package com.like.cooperation.todo.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.todo.adapter.out.persistence.TodoJpaRepository;
import com.like.cooperation.todo.application.port.dto.TodoSaveDTO;
import com.like.cooperation.todo.application.port.in.TodoQueryUseCase;
import com.like.cooperation.todo.domain.QTodo;

@Transactional(readOnly=true)
@Service
public class TodoQueryService implements TodoQueryUseCase {

	TodoJpaRepository repository;
	
	TodoQueryService(TodoJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<TodoSaveDTO> select(Long todoGroupId) {
		QTodo qTodo = QTodo.todo1;
				
		return this.repository.findAll(qTodo.todoGroup.pkTodoGroup.eq(todoGroupId))
							  .stream()
							  .map(e -> TodoSaveDTO.toDTO(e))
							  .toList();
	}

	
}
