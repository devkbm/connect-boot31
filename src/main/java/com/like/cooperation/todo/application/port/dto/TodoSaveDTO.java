package com.like.cooperation.todo.application.port.dto;

import java.time.LocalDate;

import com.like.cooperation.todo.domain.Todo;
import com.like.cooperation.todo.domain.TodoGroup;

import lombok.Builder;

@Builder
public record TodoSaveDTO(
		String clientAppUrl,
		String organizationCode,
		Long pkTodoGroup,
		Long pkTodo,
		String todo,
		boolean isCompleted,
		LocalDate dueDate,
		String comments
		) {
	
	public Todo newEntity(TodoGroup todoGroup) {
		Todo entity = Todo.builder()
						  .todoGroup(todoGroup)
						  .todo(todo)
						  .dueDate(dueDate)
						  .comments(comments)
						  .build();
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;	
	}
	
	public void modifyEntity(Todo entity) {
		entity.modify(todo, isCompleted, dueDate, comments);
		
		entity.setAppUrl(clientAppUrl);
	}
	
	public static TodoSaveDTO convert(Todo entity) {
		return TodoSaveDTO.builder()
				       .pkTodoGroup(entity.getTodoGroup().getPkTodoGroup())
				       .pkTodo(entity.getPkTodo())
				       .todo(entity.getTodo())
				       .isCompleted(entity.isCompleted())
				       .dueDate(entity.getDueDate())
				       .comments(entity.getComments())
					   .build();	
	}
}