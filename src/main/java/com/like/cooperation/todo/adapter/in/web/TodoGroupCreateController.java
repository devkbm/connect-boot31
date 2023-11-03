package com.like.cooperation.todo.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.application.port.in.TodoGroupCreateUseCase;
import com.like.cooperation.todo.domain.TodoGroup;
import com.like.system.core.util.SessionUtil;

@RestController
public class TodoGroupCreateController {

	TodoGroupCreateUseCase useCase;
	
	TodoGroupCreateController(TodoGroupCreateUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/todo/group/new")
	public ResponseEntity<?> newTodoGroup() {
										
		TodoGroup todoGroup = useCase.create(SessionUtil.getUserId());										
								 					
		return toOne(todoGroup, "생성되었습니다.");
	}
}
