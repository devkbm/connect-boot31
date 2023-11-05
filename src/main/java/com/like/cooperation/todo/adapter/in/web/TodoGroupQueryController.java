package com.like.cooperation.todo.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.application.port.in.TodoGroupQueryUseCase;
import com.like.cooperation.todo.domain.TodoGroup;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.util.SessionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TodoGroupQueryController {

	private TodoGroupQueryUseCase useCase;
	
	public TodoGroupQueryController(TodoGroupQueryUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/todo/group/mylist")
	public ResponseEntity<?> getMyTodoGroupList() {
		
		log.info(SessionUtil.getAuthentication().toString());
		
		List<TodoGroup> list = useCase.select(SessionUtil.getUserId());			 					
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
}
