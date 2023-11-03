package com.like.cooperation.todo.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.application.port.dto.TodoSaveDTO;
import com.like.cooperation.todo.application.port.in.TodoQueryUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class TodoQueryController {

	TodoQueryUseCase useCase;
	
	TodoQueryController(TodoQueryUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/todo/group/{id}/list")
	public ResponseEntity<?> getTodoList(@PathVariable Long id) {				
		
		List<TodoSaveDTO> dtoList = useCase.select(id); 											
		
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
}
