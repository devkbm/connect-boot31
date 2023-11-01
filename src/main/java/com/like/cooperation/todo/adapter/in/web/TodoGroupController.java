package com.like.cooperation.todo.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.application.port.dto.TodoDTO;
import com.like.cooperation.todo.application.service.TodoCommandService;
import com.like.cooperation.todo.domain.TodoGroup;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.util.SessionUtil;

import jakarta.validation.Valid;

@RestController
public class TodoGroupController {
	private TodoCommandService service;

	public TodoGroupController(TodoCommandService service) {		
		this.service = service;
	}
	
	@PostMapping("/api/todo/group/new")
	public ResponseEntity<?> newTodoGroup() {
										
		TodoGroup todoGroup = service.newDefaultTodoGroup(SessionUtil.getUserId());										
								 					
		return toOne(todoGroup, "생성되었습니다.");
	}
		
	@PostMapping("/api/todo/group")
	public ResponseEntity<?> saveTodoGroup(@RequestBody @Valid TodoDTO.FormTodoGroup dto) {								
			
		service.saveTodoGroup(dto);
																				 			
		return toList(null, MessageUtil.getSaveMessage(1));
	}
		
	@DeleteMapping("/api/todo/group/{id}")
	public ResponseEntity<?> deleteTodoGroup(@PathVariable Long id) {							
			
		service.deleteTodoGroup(id);
											 				
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
