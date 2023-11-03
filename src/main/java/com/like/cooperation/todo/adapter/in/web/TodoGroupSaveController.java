package com.like.cooperation.todo.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.application.port.dto.TodoGroupSaveDTO;
import com.like.cooperation.todo.application.port.in.TodoGroupSaveUseCase;
import com.like.system.core.message.MessageUtil;

import jakarta.validation.Valid;

@RestController
public class TodoGroupSaveController {

	TodoGroupSaveUseCase useCase;
	
	TodoGroupSaveController(TodoGroupSaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/todo/group")
	public ResponseEntity<?> saveTodoGroup(@RequestBody @Valid TodoGroupSaveDTO dto) {								
			
		useCase.save(dto);
																				 			
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
