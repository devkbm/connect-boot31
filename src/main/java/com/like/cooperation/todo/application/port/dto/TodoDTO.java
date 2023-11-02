package com.like.cooperation.todo.application.port.dto;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

import com.like.cooperation.todo.domain.QTodo;
import com.like.cooperation.todo.domain.QTodoGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class TodoDTO {

	public record SearchTodo(
			String userId,
			String todo,
			Boolean isCompleted,
			LocalDate dueDate
			) {
		private static final QTodoGroup qTodoGroup = QTodoGroup.todoGroup;
		private static final QTodo qTask = QTodo.todo1;		
		
		public BooleanBuilder getQueryFilter() {		
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeMenGroupCode(todo));
			
			//if (StringUtils.hasText(this.userId))
			//	builder.and(qTaskGroup.modifiedBy.eq(userId));
			
			if (StringUtils.hasText(this.todo))
				builder.and(qTask.todo.like("%"+todo+"%"));
			
			if (this.isCompleted != null)
				builder.and(qTask.isCompleted.eq(isCompleted));				 		
			
			return builder;
		}
		
		private BooleanExpression likeMenGroupCode(String todo) {
			if (!StringUtils.hasText(todo)) return null;
						
			return qTask.todo.like("%"+todo+"%");
		}
	}	
	
}
